package com.webcode.team.application.greenmovebackend.reservationManagement.application;

import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.DeleteReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.services.ReservationCommandService;
import com.webcode.team.application.greenmovebackend.reservationManagement.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final OwnerRepository ownerRepository;
    private final TenantRepository tenantRepository;
    private final VehicleRepository vehicleRepository;
    public ReservationCommandServiceImpl(ReservationRepository reservationRepository, OwnerRepository ownerRepository, TenantRepository tenantRepository, VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.ownerRepository = ownerRepository;
        this.tenantRepository = tenantRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        var reservation = new Reservation(command);
        var owner = this.ownerRepository.findById(command.ownerId());
        var tenant = this.tenantRepository.findById(command.tenantId());
        var vehicle = this.vehicleRepository.findById(command.vehicleId());

        reservation.setOwner(owner.get());
        reservation.setTenant(tenant.get());
        reservation.setVehicle(vehicle.get());

        var createdReservation = reservationRepository.save(reservation);
        return Optional.of(createdReservation);
    }

    @Override
    public void handle(DeleteReservationCommand command) {
        var reservationId = command.reservationId();
        if(!this.reservationRepository.existsById(reservationId)) {
            throw new IllegalArgumentException("Reservation with id " + reservationId + " does not exist");
        }
        try {
            this.reservationRepository.deleteById(reservationId);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error deleting reservation with id " + reservationId);
        }
    }
}
