package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources;

public record CreateTenantResource(
        String firstName,
        String lastName,
        String urlImage,
        String phone
) {
}
