package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleQueryService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.CreateVehicleResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.VehicleResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/owners/{ownerId}/vehicles")
@Tag(name = "Vehicles", description = "Vehicles endpoints")
public class VehicleController {
    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;
    public VehicleController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommand) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommand;
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle", description = "Create a new vehicle")
    public ResponseEntity<VehicleResource> createVehicle(@PathVariable Long ownerId, @RequestBody CreateVehicleResource resource) {
        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource, ownerId);
        var vehicle = vehicleCommandService.handle(createVehicleCommand);

        if(vehicle.isEmpty()) return ResponseEntity.badRequest().build();

        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());

        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping
    @Operation(summary = "Get all vehicles by owner id", description = "Get all vehicles by owner id")
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByOwnerId(@PathVariable Long ownerId) {
        var getVehiclesByOwnerIdQuery = new GetAllVehiclesByOwnerIdQuery(ownerId);
        var vehicles = vehicleQueryService.handle(getVehiclesByOwnerIdQuery);
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicleResources);
    }
}
