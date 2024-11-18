package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources;

public record CreateUserTenantResource(
        String firstName,
        String lastName,
        String urlImage,
        String phone) {
}
