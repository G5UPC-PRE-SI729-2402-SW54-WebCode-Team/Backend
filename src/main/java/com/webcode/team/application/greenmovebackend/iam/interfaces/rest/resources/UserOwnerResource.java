package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources;

public record UserOwnerResource(
        Long id,
        String fullName,
        String urlImage,
        String phone,
        String streetAddress
) {
}
