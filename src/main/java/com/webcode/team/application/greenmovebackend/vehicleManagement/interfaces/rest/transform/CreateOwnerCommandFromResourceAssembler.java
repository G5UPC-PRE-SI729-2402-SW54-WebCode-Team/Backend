package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.CreateOwnerResource;

public class CreateOwnerCommandFromResourceAssembler {
    public static CreateOwnerCommand toCommandFromResource(CreateOwnerResource resource) {
        return new CreateOwnerCommand(
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
