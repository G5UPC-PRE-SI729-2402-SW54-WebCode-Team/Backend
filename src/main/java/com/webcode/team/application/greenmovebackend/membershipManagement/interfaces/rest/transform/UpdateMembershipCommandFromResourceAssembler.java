package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.UpdateMembershipCommand;

public class UpdateMembershipCommandFromResourceAssembler {
    public static UpdateMembershipCommand toCommandFromResource(Long membershipId, String type) {
        return new UpdateMembershipCommand(membershipId, type);
    }
}
