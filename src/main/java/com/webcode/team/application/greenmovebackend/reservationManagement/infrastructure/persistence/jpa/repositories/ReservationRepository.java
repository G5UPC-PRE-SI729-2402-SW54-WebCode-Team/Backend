package com.webcode.team.application.greenmovebackend.reservationManagement.infrastructure.persistence.jpa.repositories;

import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTenantId(Long tenantId);
    List<Reservation> findByOwnerId(Long ownerId);
}
