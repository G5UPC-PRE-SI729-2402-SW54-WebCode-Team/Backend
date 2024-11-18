package com.webcode.team.application.greenmovebackend.iam.domain.model.commands;

import com.webcode.team.application.greenmovebackend.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
