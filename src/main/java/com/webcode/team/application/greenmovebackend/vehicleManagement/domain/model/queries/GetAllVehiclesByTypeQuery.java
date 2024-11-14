package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries;

/**
 * Query to get all vehicles by type
 * @param vehicleType the type of the vehicle
 */
public record GetAllVehiclesByTypeQuery(String vehicleType) {
}
