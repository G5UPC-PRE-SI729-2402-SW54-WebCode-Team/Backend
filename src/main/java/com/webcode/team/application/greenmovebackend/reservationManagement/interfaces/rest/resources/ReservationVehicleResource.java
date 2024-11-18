package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources;

public record ReservationVehicleResource(
        Long id,
        String name,
        String urlImage,
        String status,
        String type,
        String longitude,
        String latitude,
        String speed
) {
}
