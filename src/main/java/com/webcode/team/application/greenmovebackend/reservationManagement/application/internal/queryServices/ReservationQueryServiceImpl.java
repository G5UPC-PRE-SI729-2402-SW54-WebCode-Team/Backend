package com.webcode.team.application.greenmovebackend.reservationManagement.application.internal.queryServices;

import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetAllReservationsByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetAllReservationsByTenantIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetActiveReservationByTenantId;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetReservationByIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.valueObjects.ReservationStatus;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.services.ReservationQueryService;
import com.webcode.team.application.greenmovebackend.reservationManagement.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {

    private final ReservationRepository reservationRepository;
    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> handle(GetAllReservationsByTenantIdQuery query) {
        return this.reservationRepository.findByTenantId(query.tenantId());
    }

    @Override
    public List<Reservation> handle(GetAllReservationsByOwnerIdQuery query) {
        return this.reservationRepository.findByOwnerId(query.ownerId());
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return this.reservationRepository.findById(query.reservationId());
    }

    @Override
    public Optional<Reservation> handle(GetActiveReservationByTenantId query) {
        var reservation = this.reservationRepository.findByStatus(ReservationStatus.ACTIVE);
        if(reservation.isPresent() && reservation.get().getTenant().getId().equals(query.tenantId())) {
            return reservation;
        }
        return Optional.empty();
    }

}
