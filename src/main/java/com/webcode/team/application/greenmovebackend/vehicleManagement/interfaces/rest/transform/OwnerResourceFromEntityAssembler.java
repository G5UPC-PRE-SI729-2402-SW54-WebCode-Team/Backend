package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.OwnerResource;

public class OwnerResourceFromEntityAssembler {
    public static OwnerResource toResourceFromEntity(Owner owner) {
        return new OwnerResource(
                owner.getId(),
                owner.getFullName(),
                owner.getUrlImage(),
                owner.getPhone(),
                owner.getStreetAddress()
        );
    }
}
