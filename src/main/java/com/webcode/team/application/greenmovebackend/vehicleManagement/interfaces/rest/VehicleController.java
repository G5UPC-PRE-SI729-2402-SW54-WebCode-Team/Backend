package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.UpdateVehicleStatusCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByStatusQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByTypeQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetVehicleByIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleQueryService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.VehicleResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Vehicles endpoints")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
public class VehicleController {
    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;
    public VehicleController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommand) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommand;
    }

    @GetMapping("/{vehicleId}")
    @Operation(summary = "Get a vehicle by id", description = "Get a vehicle by id")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = this.vehicleQueryService.handle(getVehicleByIdQuery);

        if(vehicle.isEmpty()) return ResponseEntity.notFound().build();

        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get all vehicles by status", description = "Get all vehicles by status")
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByStatus(@PathVariable String status) {
        var getAllVehicleByStatus = new GetAllVehiclesByStatusQuery(status);
        var vehicles = this.vehicleQueryService.handle(getAllVehicleByStatus);
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get all vehicles by type", description = "Get all vehicles by type")
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByType(@PathVariable String type) {
        var getAllVehicleByType = new GetAllVehiclesByTypeQuery(type);
        var vehicles = this.vehicleQueryService.handle(getAllVehicleByType);
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicleResources);
    }

    @DeleteMapping("/{vehicleId}")
    @Operation(summary = "Delete a vehicle by id", description = "Delete a vehicle by id")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        var deleteVehicleCommand = new DeleteVehicleCommand(vehicleId);
        this.vehicleCommandService.handle(deleteVehicleCommand);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{vehicleId}/status/{status}")
    @Operation(summary = "Update vehicle status", description = "Update vehicle status")
    public ResponseEntity<VehicleResource> updateVehicleStatus(@PathVariable Long vehicleId, @PathVariable String status) {
        var updateVehicleStatusCommand = new UpdateVehicleStatusCommand(vehicleId, status);
        var vehicle = this.vehicleCommandService.handle(updateVehicleStatusCommand);
        if(vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }
}
