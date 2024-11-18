package com.webcode.team.application.greenmovebackend.iam.domain.model.commands;

public record CreateUserTenantCommand(
        Long userId,
        String firstName,
        String lastName,
        String urlImage,
        String phone
        ) {
}
