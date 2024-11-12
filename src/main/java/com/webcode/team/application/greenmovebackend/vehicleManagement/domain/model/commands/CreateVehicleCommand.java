package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

public record CreateVehicleCommand(
        String name,
        String urlImage,
        String status,
        String type,
        Long ownerId
) {
}
