package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.ReservationTenantResource;

public class ReservationTenantFromEntityAssembler {
    public static ReservationTenantResource toResourceFromEntity(Tenant entity) {
        return new ReservationTenantResource(
                entity.getId(),
                entity.getName().getFullName(),
                entity.getUrlImage(),
                entity.getPhone(),
                entity.getReservationTime()
        );
    }
}
