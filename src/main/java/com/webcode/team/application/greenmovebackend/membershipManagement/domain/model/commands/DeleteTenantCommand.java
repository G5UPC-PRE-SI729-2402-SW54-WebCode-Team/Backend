package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

/**
 * DeleteTenantCommand: Command to delete a tenant
 * @param tenantId
 */
public record DeleteTenantCommand(Long tenantId) {
}
