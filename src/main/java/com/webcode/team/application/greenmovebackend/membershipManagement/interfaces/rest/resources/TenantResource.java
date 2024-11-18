package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources;

public record TenantResource(
        Long id,
        String name,
        String urlImage,
        String phone,
        MembershipResource membership
) {
}
