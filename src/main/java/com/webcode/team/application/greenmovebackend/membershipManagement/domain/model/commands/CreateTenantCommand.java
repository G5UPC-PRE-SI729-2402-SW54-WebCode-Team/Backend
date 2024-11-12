package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

public record CreateTenantCommand(
        String firstName,
        String lastName,
        String urlImage,
        String phone
) {
}
