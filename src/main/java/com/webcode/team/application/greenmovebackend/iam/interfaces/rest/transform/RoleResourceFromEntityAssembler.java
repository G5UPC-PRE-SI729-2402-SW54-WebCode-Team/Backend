package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform;


import com.webcode.team.application.greenmovebackend.iam.domain.model.entities.Role;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
