package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform.TenantResourceFromEntityAssembler;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.ReservationResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.transform.OwnerResourceFromEntityAssembler;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation reservation) {
        return new ReservationResource(
                reservation.getId(),
                reservation.getReservationCode(),
                reservation.getStatus().name(),
                reservation.getStartDate().toString(),
                reservation.getEndDate().toString(),
                reservation.getLatitude(),
                reservation.getLongitude(),
                OwnerResourceFromEntityAssembler.toResourceFromEntity(reservation.getOwner()),
                TenantResourceFromEntityAssembler.toResourceFromEntity(reservation.getTenant()),
                ReservationVehicleFromEntityAssembler.toResourceFromEntity(reservation.getVehicle())
        );
    }
}
