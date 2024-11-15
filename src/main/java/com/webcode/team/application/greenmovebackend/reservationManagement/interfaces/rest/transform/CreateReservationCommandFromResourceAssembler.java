package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.vehicleId(),
                resource.ownerId(),
                resource.tenantId(),
                resource.latitude(),
                resource.longitude()
        );
    }
}
