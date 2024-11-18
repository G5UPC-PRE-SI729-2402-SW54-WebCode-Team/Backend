package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.UserOwnerResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;

public class UserOwnerFromEntityAssembler {
    public static UserOwnerResource toResourceFromEntity(Owner owner) {
        return new UserOwnerResource(
                owner.getId(),
                owner.getFullName(),
                owner.getUrlImage(),
                owner.getPhone(),
                owner.getStreetAddress()
        );
    }
}
