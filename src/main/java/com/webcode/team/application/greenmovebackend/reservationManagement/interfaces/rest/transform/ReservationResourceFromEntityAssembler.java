package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.TenantResourceFromEntityAssembler;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.ReservationResource;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.ReservationTenantResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.OwnerResourceFromEntityAssembler;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation reservation) {
        return new ReservationResource(
                reservation.getId(),
                reservation.getReservationCode(),
                reservation.getStatus().name(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString(),
                OwnerResourceFromEntityAssembler.toResourceFromEntity(reservation.getOwner()),
                ReservationTenantFromEntityAssembler.toResourceFromEntity(reservation.getTenant()),
                ReservationVehicleFromEntityAssembler.toResourceFromEntity(reservation.getVehicle())
        );
    }
}
