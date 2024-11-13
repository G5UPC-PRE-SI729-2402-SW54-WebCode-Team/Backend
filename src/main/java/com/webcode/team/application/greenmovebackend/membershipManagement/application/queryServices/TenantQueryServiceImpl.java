package com.webcode.team.application.greenmovebackend.membershipManagement.application.queryServices;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetAllTenantsQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetTenantByIdQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.TenantQueryService;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;

import java.util.List;
import java.util.Optional;

public class TenantQueryServiceImpl implements TenantQueryService {

    private final TenantRepository tenantRepository;
    public TenantQueryServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<Tenant> handle(GetAllTenantsQuery query) {
        return this.tenantRepository.findAll();
    }

    @Override
    public Optional<Tenant> handle(GetTenantByIdQuery query) {
        return this.tenantRepository.findById(query.tenantId());
    }
}
