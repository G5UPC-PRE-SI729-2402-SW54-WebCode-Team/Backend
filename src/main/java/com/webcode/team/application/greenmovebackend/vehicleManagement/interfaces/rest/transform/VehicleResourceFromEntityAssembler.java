package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle vehicle) {
        return new VehicleResource(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getLatitude(),
                vehicle.getLongitude(),
                vehicle.getUrlImage(),
                vehicle.getStatus().name(),
                vehicle.getType().name(),
                OwnerResourceFromEntityAssembler.toResourceFromEntity(vehicle.getOwner())
        );
    }
}
