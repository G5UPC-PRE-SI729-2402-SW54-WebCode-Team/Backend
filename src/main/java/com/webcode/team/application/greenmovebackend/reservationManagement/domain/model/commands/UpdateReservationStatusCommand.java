package com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands;

public record UpdateReservationStatusCommand(Long reservationId, String status) {
}
