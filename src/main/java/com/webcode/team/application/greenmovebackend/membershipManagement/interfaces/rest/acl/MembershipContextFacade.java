package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.acl;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateMembershipResource;

import java.util.Optional;

public interface MembershipContextFacade {
    Optional<Membership> createMembership(CreateMembershipCommand command);
}
