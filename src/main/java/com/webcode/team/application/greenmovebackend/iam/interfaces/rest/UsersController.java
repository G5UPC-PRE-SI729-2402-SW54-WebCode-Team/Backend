package com.webcode.team.application.greenmovebackend.iam.interfaces.rest;

import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserOwnerCommand;
import com.webcode.team.application.greenmovebackend.iam.domain.model.queries.GetAllUsersQuery;
import com.webcode.team.application.greenmovebackend.iam.domain.model.queries.GetUserByIdQuery;
import com.webcode.team.application.greenmovebackend.iam.domain.services.UserCommandService;
import com.webcode.team.application.greenmovebackend.iam.domain.services.UserQueryService;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.CreateUserOwnerResource;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.CreateUserTenantResource;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.UserResource;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform.CreateUserOwnerCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform.CreateUserTenantCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.OwnerCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.CreateOwnerResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.CreateOwnerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

  private final UserQueryService userQueryService;
  private final UserCommandService userCommandService;

  public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
    this.userQueryService = userQueryService;
      this.userCommandService = userCommandService;
  }

  /**
   * This method returns all the users.
   *
   * @return a list of user resources.
   * @see UserResource
   */
  @GetMapping
  public ResponseEntity<List<UserResource>> getAllUsers() {
    var getAllUsersQuery = new GetAllUsersQuery();
    var users = userQueryService.handle(getAllUsersQuery);
    var userResources = users.stream()
        .map(UserResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(userResources);
  }

  /**
   * This method returns the user with the given id.
   *
   * @param userId the user id.
   * @return the user resource with the given id
   * @throws RuntimeException if the user is not found
   * @see UserResource
   */
  @GetMapping(value = "/{userId}")
  public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var user = userQueryService.handle(getUserByIdQuery);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return ResponseEntity.ok(userResource);
  }

  @PutMapping(value = "/{userId}/owner")
  @Operation(summary = "Set user as owner", description = "Set user as owner")
  public ResponseEntity<UserResource> setUserAsOwner(@PathVariable Long userId, @RequestBody CreateUserOwnerResource resource) {
    if(this.userQueryService.handle(new GetUserByIdQuery(userId)).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var role = this.userQueryService.handle(new GetUserByIdQuery(userId)).get().getRoles().stream().findFirst().get().getName().toString();
    if(!role.equals("ROLE_OWNER")) {
      throw new RuntimeException("User is not an owner");
    }
    var createUserOwnerCommand = CreateUserOwnerCommandFromResourceAssembler.toCommandFromResource(resource, userId);
    var user = userCommandService.handle(createUserOwnerCommand);
    if(user.isEmpty()) return ResponseEntity.badRequest().build();
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return ResponseEntity.ok(userResource);
  }

  @PutMapping(value = "/{userId}/tenant")
  @Operation(summary = "Set user as tenant", description = "Set user as tenant")
  public ResponseEntity<UserResource> setUserAsTenant(@PathVariable Long userId, @RequestBody CreateUserTenantResource resource){
    if(this.userQueryService.handle(new GetUserByIdQuery(userId)).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var role = this.userQueryService.handle(new GetUserByIdQuery(userId)).get().getRoles().stream().findFirst().get().getName().toString();
    if(!role.equals("ROLE_TENANT")) {
      throw new RuntimeException("User is not a tenant");
    }
    var createUserTenantCommand = CreateUserTenantCommandFromResourceAssembler.toCommandFromResource(resource, userId);
    var user = userCommandService.handle(createUserTenantCommand);
    if(user.isEmpty()) return ResponseEntity.badRequest().build();
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return ResponseEntity.ok(userResource);
  }
}
