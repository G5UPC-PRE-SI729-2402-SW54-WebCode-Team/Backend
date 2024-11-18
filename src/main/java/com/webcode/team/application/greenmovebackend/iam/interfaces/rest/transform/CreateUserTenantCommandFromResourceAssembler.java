package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserTenantCommand;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.CreateUserTenantResource;

public class CreateUserTenantCommandFromResourceAssembler {
    public static CreateUserTenantCommand toCommandFromResource(CreateUserTenantResource resource, Long userId) {
        return new CreateUserTenantCommand(
                userId,
                resource.firstName(),
                resource.lastName(),
                resource.urlImage(),
                resource.phone()
        );
    }
}
