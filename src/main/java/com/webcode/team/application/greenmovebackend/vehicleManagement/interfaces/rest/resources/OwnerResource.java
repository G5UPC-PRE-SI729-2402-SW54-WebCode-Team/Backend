package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources;

public record OwnerResource(
        Long id,
        String fullName,
        String urlImage,
        String phone,
        String streetAddress
) {
}
