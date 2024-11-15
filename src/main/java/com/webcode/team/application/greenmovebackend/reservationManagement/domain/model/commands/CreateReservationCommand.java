package com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands;

public record CreateReservationCommand(
        Long vehicleId,
        Long ownerId,
        Long tenantId,
        String latitude,
        String longitude
) {
}
