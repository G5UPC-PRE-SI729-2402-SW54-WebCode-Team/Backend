package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetOwnerByIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.OwnerCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.OwnerQueryService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.CreateOwnerResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.OwnerResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.CreateOwnerCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.OwnerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/owners")
@Tag(name = "Owner", description = "Owner endpoints")
public class OwnerController {
    private final OwnerQueryService ownerQueryService;
    private final OwnerCommandService ownerCommandService;

    public OwnerController(OwnerQueryService ownerQueryService, OwnerCommandService ownerCommandService) {
        this.ownerQueryService = ownerQueryService;
        this.ownerCommandService = ownerCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new owner", description = "Create a new owner")
    public ResponseEntity<OwnerResource> createOwner(@RequestBody CreateOwnerResource resource){
        var createOwnerCommand = CreateOwnerCommandFromResourceAssembler.toCommandFromResource(resource);
        var owner = ownerCommandService.handle(createOwnerCommand);

        if(owner.isEmpty()) return ResponseEntity.badRequest().build();

        var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());

        return new ResponseEntity<>(ownerResource, HttpStatus.CREATED);
    }
    @GetMapping("/{ownerId}")
    @Operation(summary = "Get owner by id", description = "Get owner by id")
    public ResponseEntity<OwnerResource> getOwnerById(@PathVariable Long ownerId){
        var getOwnerByIdQuery = new GetOwnerByIdQuery(ownerId);
        var owner = ownerQueryService.handle(getOwnerByIdQuery);

        if(owner.isEmpty()) return ResponseEntity.notFound().build();

        var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());

        return ResponseEntity.ok(ownerResource);
    }
    @DeleteMapping("/{ownerId}")
    @Operation(summary = "Delete owner by id", description = "Delete owner by id")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long ownerId){
        var deleteOwnerCommand = new DeleteOwnerCommand(ownerId);
        ownerCommandService.handle(deleteOwnerCommand);
        return ResponseEntity.noContent().build();
    }
}
