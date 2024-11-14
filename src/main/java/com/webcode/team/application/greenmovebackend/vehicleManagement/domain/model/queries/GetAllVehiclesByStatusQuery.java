package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries;

/**
 * Query to get all vehicles by status
 * @param vehicleStatus the status of the vehicle
 */
public record GetAllVehiclesByStatusQuery(String vehicleStatus) {
}
