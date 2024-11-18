package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetMembershipByTenantIdQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.MembershipCommandService;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.MembershipQueryService;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateMembershipResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.MembershipResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.CreateMembershipCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.MembershipResourceFromEntityAssembler;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.UpdateMembershipCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/tenant/{tenantId}/membership")
@Tag(name = "Membership Management", description = "Membership Management API")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
public class TenantMembershipController {
    private final MembershipQueryService membershipQueryService;
    private final MembershipCommandService membershipCommandService;

    public TenantMembershipController (MembershipQueryService membershipQueryService, MembershipCommandService membershipCommandService) {
        this.membershipQueryService = membershipQueryService;
        this.membershipCommandService = membershipCommandService;
    }

    @GetMapping
    @Operation(summary = "Get membership by tenant id", description = "Returns a membership by tenant id.")
    public ResponseEntity<MembershipResource> getMembershipByTenantId(@PathVariable Long tenantId){
        var getMembershipByTenantIdQuery = new GetMembershipByTenantIdQuery(tenantId);
        var membership = this.membershipQueryService.handle(getMembershipByTenantIdQuery);
        if(membership.isEmpty()) return ResponseEntity.notFound().build();
        var membershipResource = MembershipResourceFromEntityAssembler.toResourceFromEntity(membership.get());
        return ResponseEntity.ok(membershipResource);
    }

    @PostMapping
    @Operation(summary = "Create a new membership", description = "Creates a new membership with the provided details.")
    public ResponseEntity<MembershipResource> createMembership(@PathVariable Long tenantId, @RequestBody CreateMembershipResource resource){
        var createMembershipCommand = CreateMembershipCommandFromResourceAssembler.toCommandFromResource(resource, tenantId);
        var membership = membershipCommandService.handle(createMembershipCommand);
        if(membership.isEmpty()) return ResponseEntity.badRequest().build();
        var membershipResource = MembershipResourceFromEntityAssembler.toResourceFromEntity(membership.get());
        return ResponseEntity.ok(membershipResource);
    }

    @PutMapping
    @Operation(summary = "Update a membership", description = "Updates a membership with the provided details.")
    public ResponseEntity<MembershipResource> updateMembership(@PathVariable Long tenantId, @RequestBody CreateMembershipResource resource){
        var membershipToUpdate = this.membershipQueryService.handle(new GetMembershipByTenantIdQuery(tenantId));
        var updateMembershipCommand = UpdateMembershipCommandFromResourceAssembler.toCommandFromResource(membershipToUpdate.get().getId(), resource.type());
        var membership = this.membershipCommandService.handle(updateMembershipCommand);
        if(membership.isEmpty()) return ResponseEntity.badRequest().build();
        var membershipResource = MembershipResourceFromEntityAssembler.toResourceFromEntity(membership.get());
        return ResponseEntity.ok(membershipResource);
    }

}
