package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources;

public record UserTenantResource(
        Long id,
        String fullName,
        String urlImage,
        String phone
) {
}
