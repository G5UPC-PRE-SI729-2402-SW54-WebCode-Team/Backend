package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.acl;


import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;

import java.util.Optional;

public interface VehicleContextFacade {
    void updateVehicleStatus(Long vehicleId, String status);
    boolean isVehicleAvailable(Long vehicleId);
    boolean isOwnerOfVehicle(Long vehicleId, Long ownerId);
    Optional<Vehicle> getVehicleById(Long vehicleId);
}
