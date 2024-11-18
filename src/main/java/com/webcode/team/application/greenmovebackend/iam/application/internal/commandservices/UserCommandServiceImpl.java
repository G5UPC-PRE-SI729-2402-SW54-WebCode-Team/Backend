package com.webcode.team.application.greenmovebackend.iam.application.internal.commandservices;


import com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.hashing.HashingService;
import com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.memberships.ExternalMembershipService;
import com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.owners.ExternalOwnerService;
import com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.tenants.ExternalTenantService;
import com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.tokens.TokenService;
import com.webcode.team.application.greenmovebackend.iam.domain.model.aggregates.User;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserOwnerCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserTenantCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.SignInCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.SignUpCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.services.UserCommandService;
import com.webcode.team.application.greenmovebackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.webcode.team.application.greenmovebackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateMembershipResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.CreateMembershipCommandFromResourceAssembler;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

  private final UserRepository userRepository;
  private final HashingService hashingService;
  private final TokenService tokenService;
  private final RoleRepository roleRepository;

  private final ExternalOwnerService externalOwnerService;
  private final ExternalTenantService externalTenantService;
  private final ExternalMembershipService externalMembershipService;

  public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
                                TokenService tokenService, RoleRepository roleRepository, ExternalOwnerService externalOwnerService, ExternalTenantService externalTenantService, ExternalMembershipService externalMembershipService) {

    this.userRepository = userRepository;
    this.hashingService = hashingService;
    this.tokenService = tokenService;
    this.roleRepository = roleRepository;
    this.externalOwnerService = externalOwnerService;
    this.externalTenantService = externalTenantService;
    this.externalMembershipService = externalMembershipService;
  }

  /**
   * Handle the sign-in command
   * <p>
   *     This method handles the {@link SignInCommand} command and returns the user and the token.
   * </p>
   * @param command the sign-in command containing the username and password
   * @return and optional containing the user matching the username and the generated token
   * @throws RuntimeException if the user is not found or the password is invalid
   */
  @Override
  public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
    var user = userRepository.findByUsername(command.username());
    if (user.isEmpty())
      throw new RuntimeException("User not found");
    if (!hashingService.matches(command.password(), user.get().getPassword()))
      throw new RuntimeException("Invalid password");

    var token = tokenService.generateToken(user.get().getUsername());
    return Optional.of(ImmutablePair.of(user.get(), token));
  }

  /**
   * Handle the sign-up command
   * <p>
   *     This method handles the {@link SignUpCommand} command and returns the user.
   * </p>
   * @param command the sign-up command containing the username and password
   * @return the created user
   */
  @Override
  public Optional<User> handle(SignUpCommand command) {
    if (userRepository.existsByUsername(command.username()))
      throw new RuntimeException("Username already exists");
    var roles = command.roles().stream()
        .map(role ->
            roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role name not found")))
        .toList();
    var user = new User(command.username(), hashingService.encode(command.password()), roles);
    userRepository.save(user);
    return userRepository.findByUsername(command.username());
  }

  @Override
  public Optional<User> handle(CreateUserOwnerCommand command) {
    var userId = command.userId();
    if (userRepository.findById(userId).isEmpty()) {
      throw new RuntimeException("User not found");
    }
    var user = userRepository.findById(userId).get();
    var owner = externalOwnerService.createUserOwner(command);
    user.setOwner(owner.get());
    try {
      var updatedUser = this.userRepository.save(user);
      return Optional.of(updatedUser);
    } catch (Exception e) {
      throw new RuntimeException("Error setting user as owner");
    }
  }

  @Override
  public Optional<User> handle(CreateUserTenantCommand command) {
    var userId = command.userId();
    if (userRepository.findById(userId).isEmpty()) {
      throw new RuntimeException("User not found");
    }
    var user = userRepository.findById(userId).get();
    var tenant = externalTenantService.createUserTenant(command);
    var createMembershipCommand = CreateMembershipCommandFromResourceAssembler.toCommandFromResource(new CreateMembershipResource("DEFAULT"), tenant.get().getId());
    this.externalMembershipService.createMembership(createMembershipCommand);
    user.setTenant(tenant.get());
    try {
      var updatedUser = this.userRepository.save(user);
      return Optional.of(updatedUser);
    } catch (Exception e) {
      throw new RuntimeException("Error setting user as tenant");
    }
  }
}
