package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources;

public record CreateReservationResource(
        Long vehicleId,
        Long ownerId,
        Long tenantId
) {
}
