package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateTenantCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateTenantResource;

public class CreateTenantCommandFromResourceAssembler{
    public static CreateTenantCommand toCommandFromResource(CreateTenantResource resource){
        return new CreateTenantCommand(
                resource.firstName(),
                resource.lastName(),
                resource.urlImage(),
                resource.phone()
        );
    }
}
