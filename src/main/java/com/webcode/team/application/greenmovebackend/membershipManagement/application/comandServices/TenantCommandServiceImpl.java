package com.webcode.team.application.greenmovebackend.membershipManagement.application.comandServices;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateTenantCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.DeleteTenantCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.TenantCommandService;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantCommandServiceImpl implements TenantCommandService {

    private final TenantRepository tenantRepository;
    public TenantCommandServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Optional<Tenant> handle(CreateTenantCommand command) {
        var tenant = new Tenant(command);
        var createdTenant = tenantRepository.save(tenant);
        return Optional.of(createdTenant);
    }

    @Override
    public void handle(DeleteTenantCommand command) {
        var tenantId = command.tenantId();
        if(!this.tenantRepository.existsById(tenantId)) {
            throw new IllegalArgumentException("Tenant with id " + tenantId + " does not exist");
        }
        try{
            this.tenantRepository.deleteById(tenantId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting tenant with id " + tenantId);
        }
    }
}
