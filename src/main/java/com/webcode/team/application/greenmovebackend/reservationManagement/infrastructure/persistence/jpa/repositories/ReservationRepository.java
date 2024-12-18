package com.webcode.team.application.greenmovebackend.reservationManagement.infrastructure.persistence.jpa.repositories;

import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.valueObjects.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTenantId(Long tenantId);
    List<Reservation> findByOwnerId(Long ownerId);
    Optional<Reservation> findByStatus(ReservationStatus status);
}
