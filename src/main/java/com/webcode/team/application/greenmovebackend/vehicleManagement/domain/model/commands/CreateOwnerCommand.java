package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

public record CreateOwnerCommand(
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
