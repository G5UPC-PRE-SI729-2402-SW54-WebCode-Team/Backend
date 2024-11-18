package com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.memberships;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.acl.MembershipContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalMembershipService {
    private final MembershipContextFacade membershipContextFacade;

    public ExternalMembershipService(MembershipContextFacade membershipContextFacade) {
        this.membershipContextFacade = membershipContextFacade;
    }

    public Optional<Membership> createMembership(CreateMembershipCommand command) {
        var membership = this.membershipContextFacade.createMembership(command);
        if(membership.isEmpty()) {
            throw new IllegalArgumentException("Error creating membership");
        }
        return membership;
    }
}
