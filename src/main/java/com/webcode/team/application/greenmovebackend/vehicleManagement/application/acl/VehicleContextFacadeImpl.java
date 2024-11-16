package com.webcode.team.application.greenmovebackend.vehicleManagement.application.acl;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.UpdateVehicleStatusCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetVehicleByIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleQueryService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.acl.VehicleContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleContextFacadeImpl implements VehicleContextFacade {

    private final VehicleCommandService vehicleCommandService;
    private final VehicleQueryService vehicleQueryService;

    public VehicleContextFacadeImpl(VehicleCommandService vehicleCommandService, VehicleQueryService vehicleQueryService) {
        this.vehicleCommandService = vehicleCommandService;
        this.vehicleQueryService = vehicleQueryService;
    }


    @Override
    public void updateVehicleStatus(Long vehicleId, String status) {
        var updateVehicleStatusCommand = new UpdateVehicleStatusCommand(vehicleId, status);
        this.vehicleCommandService.handle(updateVehicleStatusCommand);
    }

    @Override
    public boolean isVehicleAvailable(Long vehicleId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = this.vehicleQueryService.handle(getVehicleByIdQuery);
        if(vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        return vehicle.map(value -> value.getStatus().toString().equals("AVAILABLE")).orElse(false);
    }

    @Override
    public boolean isOwnerOfVehicle(Long vehicleId, Long ownerId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = this.vehicleQueryService.handle(getVehicleByIdQuery);
        if(vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        return vehicle.map(value -> value.getOwner().getId().equals(ownerId)).orElse(false);
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long vehicleId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = this.vehicleQueryService.handle(getVehicleByIdQuery);
        if(vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        return vehicle;
    }
}
