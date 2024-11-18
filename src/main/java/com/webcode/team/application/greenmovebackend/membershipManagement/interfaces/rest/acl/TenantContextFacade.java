package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.acl;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;

import java.util.Optional;

public interface TenantContextFacade {
    Optional<Tenant> createTenant(String firstName, String lastName, String urlImage, String phone);
}
