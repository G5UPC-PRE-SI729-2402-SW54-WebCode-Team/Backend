package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources;

public record CreateOwnerResource(
        String firstName,
        String lastName,
        String urlImage,
        String phone,
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {
}
