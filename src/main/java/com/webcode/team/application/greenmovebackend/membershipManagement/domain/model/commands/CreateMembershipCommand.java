package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

/**
 * CreateMembershipCommand: Command to create a membership
 * @param type
 * @param tenantId
 */
public record CreateMembershipCommand(
    String type,
    Long tenantId
) {
}
