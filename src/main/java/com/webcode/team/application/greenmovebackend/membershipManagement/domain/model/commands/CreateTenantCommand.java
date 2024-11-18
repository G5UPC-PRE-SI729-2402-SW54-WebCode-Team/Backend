package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

/**
 * CreateTenantCommand: Command to create a tenant
 * @param firstName
 * @param lastName
 * @param urlImage
 * @param phone
 */
public record CreateTenantCommand(
        String firstName,
        String lastName,
        String urlImage,
        String phone
) {
}
