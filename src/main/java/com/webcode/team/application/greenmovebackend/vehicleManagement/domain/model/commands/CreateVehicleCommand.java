package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

/**
 * CreateVehicleCommand
 * @summary
 * This record represents the command to create a vehicle.
 * @param name the name of the vehicle
 * @param urlImage the url of the image of the vehicle
 * @param status the status of the vehicle
 * @param type the type of the vehicle
 * @param ownerId the id of the owner of the vehicle
 * @since 1.0
 */
public record CreateVehicleCommand(
        String name,
        String urlImage,
        String status,
        String type,
        Long ownerId
) {
}
