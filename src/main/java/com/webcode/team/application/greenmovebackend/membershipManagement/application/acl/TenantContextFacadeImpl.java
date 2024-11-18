package com.webcode.team.application.greenmovebackend.membershipManagement.application.acl;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.acl.TenantContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantContextFacadeImpl implements TenantContextFacade {
    private final TenantRepository tenantRepository;

    public TenantContextFacadeImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Optional<Tenant> createTenant(String firstName, String lastName, String urlImage, String phone) {
        var tenant = new Tenant(firstName, lastName, urlImage, phone);
        var createdTenant = this.tenantRepository.save(tenant);
        return Optional.of(createdTenant);
    }
}
