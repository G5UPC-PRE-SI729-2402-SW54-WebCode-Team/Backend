package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateMembershipResource;

public class CreateMembershipCommandFromResourceAssembler{
    public static CreateMembershipCommand toCommandFromResource(CreateMembershipResource resource, Long tenantId){
        return new CreateMembershipCommand(
            resource.type(), tenantId
        );
    }
}
