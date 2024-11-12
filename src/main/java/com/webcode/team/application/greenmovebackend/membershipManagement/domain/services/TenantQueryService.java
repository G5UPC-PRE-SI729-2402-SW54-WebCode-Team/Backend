package com.webcode.team.application.greenmovebackend.membershipManagement.domain.services;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetAllTenantsQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetTenantByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TenantQueryService {
    List<Tenant> handle(GetAllTenantsQuery query);
    Optional<Tenant> handle(GetTenantByIdQuery query);
}
