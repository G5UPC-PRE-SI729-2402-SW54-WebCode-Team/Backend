package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

public record UpdateVehicleStatusCommand(Long vehicleId, String status) {
}
