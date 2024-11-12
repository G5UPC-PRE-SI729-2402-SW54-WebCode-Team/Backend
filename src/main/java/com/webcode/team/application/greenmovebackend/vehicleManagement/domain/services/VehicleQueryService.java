package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetVehicleByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Vehicle query service interface: handle queries related to vehicles
 * <br>
 * It is used to query vehicles by owner id and vehicle id
 * <br>
 * GetAllVehiclesByOwnerIdQuery: query to get all vehicles by owner id, return list of vehicles
 * <br>
 * GetVehicleByIdQuery: query to get vehicle by vehicle id, return optional vehicle
 */

public interface VehicleQueryService {
    List<Vehicle> handle(GetAllVehiclesByOwnerIdQuery query);
    Optional<Vehicle> handle(GetVehicleByIdQuery query);
}
