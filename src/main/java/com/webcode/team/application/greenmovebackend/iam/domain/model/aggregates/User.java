package com.webcode.team.application.greenmovebackend.iam.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.iam.domain.model.entities.Role;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

  @NotBlank
  @Size(max = 50)
  @Column(unique = true)
  private String username;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(	name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @Nullable
  @Setter
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @Nullable
  @Setter
  @ManyToOne
  @JoinColumn(name = "tenant_id")
  private Tenant tenant;

  public User() {
    this.roles = new HashSet<>();
  }
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.roles = new HashSet<>();
  }

  public User(String username, String password, List<Role> roles) {
    this(username, password);
    addRoles(roles);
  }

  /**
   * Add a role to the user
   * @param role the role to add
   * @return the user with the added role
   */
  public User addRole(Role role) {
    this.roles.add(role);
    return this;
  }

  /**
   * Add a list of roles to the user
   * @param roles the list of roles to add
   * @return the user with the added roles
   */
  public User addRoles(List<Role> roles) {
    var validatedRoleSet = Role.validateRoleSet(roles);
    this.roles.addAll(validatedRoleSet);
    return this;
  }
}