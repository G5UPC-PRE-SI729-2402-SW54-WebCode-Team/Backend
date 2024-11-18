package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserOwnerCommand;
import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.CreateUserOwnerResource;

public class CreateUserOwnerCommandFromResourceAssembler {
    public static CreateUserOwnerCommand toCommandFromResource(CreateUserOwnerResource resource, Long userId) {
        return new CreateUserOwnerCommand(
                userId,
                resource.firstName(),
                resource.lastName(),
                resource.urlImage(),
                resource.phone(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country()
        );
    }
}
