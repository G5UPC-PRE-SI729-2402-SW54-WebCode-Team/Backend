package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources;

public record VehicleResource(
        Long id,
        String name,
        String urlImage,
        String status,
        String type,
        OwnerResource owner
) {
}
