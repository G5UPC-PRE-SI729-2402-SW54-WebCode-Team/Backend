package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

/**
 * Command to delete a vehicle
 * @summary This record represents the command to delete a vehicle.
 * @param vehicleId The vehicle id
 * @since 1.0
 */
public record DeleteVehicleCommand(Long vehicleId) {
}
