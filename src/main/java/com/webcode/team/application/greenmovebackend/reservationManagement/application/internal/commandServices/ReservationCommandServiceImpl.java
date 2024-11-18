package com.webcode.team.application.greenmovebackend.reservationManagement.application.internal.commandServices;

import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;
import com.webcode.team.application.greenmovebackend.reservationManagement.application.internal.outboundServices.acl.ExternalVehicleService;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates.Reservation;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.DeleteReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.UpdateReservationStatusCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.valueObjects.ReservationStatus;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.services.ReservationCommandService;
import com.webcode.team.application.greenmovebackend.reservationManagement.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final OwnerRepository ownerRepository;
    private final TenantRepository tenantRepository;
    private final ExternalVehicleService externalVehicleService;
    public ReservationCommandServiceImpl(ReservationRepository reservationRepository, OwnerRepository ownerRepository, TenantRepository tenantRepository, ExternalVehicleService externalVehicleService) {
        this.reservationRepository = reservationRepository;
        this.ownerRepository = ownerRepository;
        this.tenantRepository = tenantRepository;
        this.externalVehicleService = externalVehicleService;
    }

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        var reservation = new Reservation(command);
        var owner = this.ownerRepository.findById(command.ownerId());
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner with id " + command.ownerId() + " does not exist");
        }
        var tenant = this.tenantRepository.findById(command.tenantId());
        if (tenant.isEmpty()) {
            throw new IllegalArgumentException("Tenant with id " + command.tenantId() + " does not exist");
        }
        var vehicle = this.externalVehicleService.fetchVehicleById(command.vehicleId());
        if (vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle with id " + command.vehicleId() + " does not exist");
        }

        var activeReservation = this.reservationRepository.findByStatus(ReservationStatus.ACTIVE);
        if(activeReservation.isPresent() && activeReservation.get().getTenant().getId().equals(tenant.get().getId())) {
            throw new IllegalArgumentException("There is an active reservation for tenant with id " + command.tenantId());
        }

        if(!this.externalVehicleService.isOwnerOfVehicle(command.vehicleId(), command.ownerId())) {
            throw new IllegalArgumentException("Owner with id " + command.ownerId() + " is not the owner of vehicle with id " + command.vehicleId());
        }else {
            reservation.setOwner(owner.get());
        }

        if(!this.externalVehicleService.isVehicleAvailable(command.vehicleId())) {
            throw new IllegalArgumentException("Vehicle with id " + command.vehicleId() + " is not available");
        }else{
            reservation.setVehicle(vehicle.get());
            this.externalVehicleService.updateVehicleStatus(command.vehicleId(), "ON_TRIP");
        }
        reservation.setTenant(tenant.get());


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

    @Override
    public Optional<Reservation> handle(UpdateReservationStatusCommand command) {
        var reservationId = command.reservationId();
        if(!this.reservationRepository.existsById(reservationId)) {
            throw new IllegalArgumentException("Reservation with id " + reservationId + " does not exist");
        }
        var reservationToUpdate = this.reservationRepository.findById(reservationId).get();
        var reservationVehicleId = reservationToUpdate.getVehicle().getId();
        if (command.status().equals("COMPLETED")) {
            this.externalVehicleService.updateVehicleStatus(reservationVehicleId, "AVAILABLE");
        }

        reservationToUpdate.updateStatus(command);
        try {
            var updatedReservation = this.reservationRepository.save(reservationToUpdate);
            return Optional.of(updatedReservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating reservation with id " + reservationId);
        }


    }
}
