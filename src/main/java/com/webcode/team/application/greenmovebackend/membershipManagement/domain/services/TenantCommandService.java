package com.webcode.team.application.greenmovebackend.membershipManagement.domain.services;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateTenantCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.DeleteTenantCommand;

import java.util.Optional;

public interface TenantCommandService {
    Optional<Tenant> handle(CreateTenantCommand command);
    void handle(DeleteTenantCommand command);
}
