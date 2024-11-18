package com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.tenants;

import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserTenantCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.acl.TenantContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalTenantService {
    private final TenantContextFacade tenantContextFacade;

    public ExternalTenantService(TenantContextFacade tenantContextFacade) {
        this.tenantContextFacade = tenantContextFacade;
    }

    public Optional<Tenant> createUserTenant(CreateUserTenantCommand command) {
        var tenant = this.tenantContextFacade.createTenant(command.firstName(), command.lastName(), command.urlImage(), command.phone());
        if(tenant.isEmpty()) {
            throw new IllegalArgumentException("Error creating tenant");
        }
        return tenant;
    }
}
