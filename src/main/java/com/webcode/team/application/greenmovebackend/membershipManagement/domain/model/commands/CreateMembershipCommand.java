package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

public record CreateMembershipCommand(
    String type,
    Long tenantId
) {
}
