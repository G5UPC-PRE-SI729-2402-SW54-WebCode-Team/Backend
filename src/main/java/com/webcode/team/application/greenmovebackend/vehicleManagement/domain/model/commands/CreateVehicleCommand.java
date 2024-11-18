package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

/**
 * CreateVehicleCommand
 * @summary
 * This record represents the command to create a vehicle.
 * @param name the name of the vehicle
 * @param latitude the latitude of the vehicle
 * @param longitude the longitude of the vehicle
 * @param urlImage the url of the image of the vehicle
 * @param type the type of the vehicle
 * @param ownerId the id of the owner of the vehicle
 * @since 1.0
 */
public record CreateVehicleCommand(
        String name,
        String latitude,
        String longitude,
        String speed,
        String urlImage,
        String type,
        Long ownerId
) {
}
