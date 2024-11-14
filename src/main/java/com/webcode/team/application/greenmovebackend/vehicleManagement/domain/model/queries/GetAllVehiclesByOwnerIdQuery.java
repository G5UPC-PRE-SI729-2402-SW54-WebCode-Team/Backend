package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries;

/**
 * Query to get all vehicles by owner id
 * @param ownerId the owner id
 * @version 1.0
 */
public record GetAllVehiclesByOwnerIdQuery(Long ownerId) {
}
