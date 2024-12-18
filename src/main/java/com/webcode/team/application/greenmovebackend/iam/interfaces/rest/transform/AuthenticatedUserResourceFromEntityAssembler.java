package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.iam.domain.model.aggregates.User;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
  }
}
