package com.webcode.team.application.greenmovebackend.reservationManagement.domain.services;

import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetAllReservationsByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetAllReservationsByTenantIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetReservationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    List<Reservation> handle(GetAllReservationsByTenantIdQuery query);
    List<Reservation> handle(GetAllReservationsByOwnerIdQuery query);
    Optional<Reservation> handle(GetReservationByIdQuery query);
}
