package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetVehicleByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Vehicle query service interface: handle queries related to vehicles
 * @version 1.0
 */

public interface VehicleQueryService {
    /**
     * Handle get all vehicles by owner id query
     * @param query the get all vehicles by owner id query
     * @return the list of vehicles
     */
    List<Vehicle> handle(GetAllVehiclesByOwnerIdQuery query);
    /**
     * Handle get vehicle by id query
     * @param query the get vehicle by id query
     * @return the vehicle
     */
    Optional<Vehicle> handle(GetVehicleByIdQuery query);
}
