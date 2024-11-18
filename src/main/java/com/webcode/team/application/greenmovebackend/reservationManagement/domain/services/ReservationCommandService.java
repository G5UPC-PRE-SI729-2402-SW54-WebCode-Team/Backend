package com.webcode.team.application.greenmovebackend.reservationManagement.domain.services;

import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.DeleteReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.UpdateReservationStatusCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Optional<Reservation> handle(CreateReservationCommand command);
    void handle(DeleteReservationCommand command);
    Optional<Reservation> handle(UpdateReservationStatusCommand command);
}
