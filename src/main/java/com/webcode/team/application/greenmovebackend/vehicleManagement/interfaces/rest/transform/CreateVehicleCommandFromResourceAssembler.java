package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource, Long ownerId) {
        return new CreateVehicleCommand(
                resource.name(),
                resource.urlImage(),
                resource.type(),
                ownerId
        );
    }
}
