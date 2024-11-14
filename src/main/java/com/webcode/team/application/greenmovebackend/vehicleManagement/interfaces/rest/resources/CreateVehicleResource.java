package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources;

public record CreateVehicleResource(
        String name,
        String urlImage,
        String status,
        String type
) {
}
