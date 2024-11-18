package com.webcode.team.application.greenmovebackend.membershipManagement.domain.services;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.DeleteMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.UpdateMembershipCommand;

import java.util.Optional;

public interface MembershipCommandService {
    Optional<Membership> handle(CreateMembershipCommand command);
    void handle(DeleteMembershipCommand command);
    Optional<Membership> handle(UpdateMembershipCommand command);
}
