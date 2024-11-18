package com.webcode.team.application.greenmovebackend.iam.domain.services;

import com.webcode.team.application.greenmovebackend.iam.domain.model.aggregates.User;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserOwnerCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserTenantCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.SignInCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
  /**
   * Handle sign in command
   * This method handles the sign in command and returns the user and the token
   * @param command
   * @return user and token
   */
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);
  /**
   * Handle sign up command
   * This method handles the sign up command and returns the user
   * @param command
   * @return user
   */
  Optional<User> handle(SignUpCommand command);

  /**
   * Handle create user owner command
   * This method handles the create user owner command and returns the user
   * @param command
   * @return user
   */
  Optional<User> handle(CreateUserOwnerCommand command);

  /**
   * Handle create user tenant command
   * This method handles the create user tenant command and returns the user
   * @param command
   * @return user
   */
  Optional<User> handle(CreateUserTenantCommand command);
}
