package com.webcode.team.application.greenmovebackend.iam.domain.services;


import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
