package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.DeleteTenantCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetAllTenantsQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetTenantByIdQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.MembershipCommandService;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.TenantCommandService;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.TenantQueryService;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateMembershipResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateTenantResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.TenantResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.CreateMembershipCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.CreateTenantCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.TenantResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/tenants")
@Tag(name = "Tenant Management", description = "Tenant Management API")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
public class TenantController {
    private final TenantQueryService tenantQueryService;
    private final TenantCommandService tenantCommandService;
    private final MembershipCommandService membershipCommandService;
    public TenantController(TenantQueryService tenantQueryService, TenantCommandService tenantCommandService, MembershipCommandService membershipCommandService) {
        this.tenantQueryService = tenantQueryService;
        this.tenantCommandService = tenantCommandService;
        this.membershipCommandService = membershipCommandService;
    }

    @GetMapping
    @Operation(summary = "Get all tenants", description = "Returns a list of all tenants.")
    public ResponseEntity<List<TenantResource>> getAllTenants(){
        var getAllTenantsQuery = new GetAllTenantsQuery();
        var tenants = this.tenantQueryService.handle(getAllTenantsQuery);
        var tenantResources = tenants.stream()
                .map(TenantResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tenantResources);
    }

    @GetMapping("/{tenantId}")
    @Operation(summary = "Get tenant by id", description = "Returns a tenant by id.")
    public ResponseEntity<TenantResource> getTenantById(@PathVariable Long tenantId){
        var getTenantByIdQuery = new GetTenantByIdQuery(tenantId);
        var tenant = this.tenantQueryService.handle(getTenantByIdQuery);
        if(tenant.isEmpty()) return ResponseEntity.notFound().build();
        var tenantResource = TenantResourceFromEntityAssembler.toResourceFromEntity(tenant.get());
        return ResponseEntity.ok(tenantResource);
    }
    @DeleteMapping("/{tenantId}")
    @Operation(summary = "Delete tenant by id", description = "Deletes a tenant by id.")
    public ResponseEntity<Void> deleteTenantById(@PathVariable Long tenantId){
        var deletedTenantCommand = new DeleteTenantCommand(tenantId);
        this.tenantCommandService.handle(deletedTenantCommand);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Create a new tenant", description = "Creates a new tenant with the provided details.")
    public ResponseEntity<TenantResource> createTenant(@RequestBody CreateTenantResource resource){
        var createTenantCommand = CreateTenantCommandFromResourceAssembler.toCommandFromResource(resource);
        var tenant = this.tenantCommandService.handle(createTenantCommand);
        if(tenant.isEmpty()) return ResponseEntity.badRequest().build();

        var createMembershipCommand = CreateMembershipCommandFromResourceAssembler.toCommandFromResource(new CreateMembershipResource("DEFAULT"), tenant.get().getId());
        this.membershipCommandService.handle(createMembershipCommand);

        var tenantResource = TenantResourceFromEntityAssembler.toResourceFromEntity(tenant.get());
        return ResponseEntity.ok(tenantResource);
    }
}
