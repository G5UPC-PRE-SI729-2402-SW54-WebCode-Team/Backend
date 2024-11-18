package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources;

public record CreateUserOwnerResource(
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
