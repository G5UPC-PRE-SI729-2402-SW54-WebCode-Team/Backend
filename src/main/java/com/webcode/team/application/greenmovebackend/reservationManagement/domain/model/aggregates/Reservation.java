package com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.CreateReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.UpdateReservationStatusCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.valueObjects.ReservationStatus;
import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    private String reservationCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String latitude;
    private String longitude;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Setter
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Setter
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Reservation() {
        this.reservationCode = UUID.randomUUID().toString();
        this.status = ReservationStatus.ACTIVE;
    }

    public Reservation(CreateReservationCommand command) {
        this.reservationCode = UUID.randomUUID().toString();
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusHours(4);
        this.latitude = command.latitude();
        this.longitude = command.longitude();
        this.status = ReservationStatus.ACTIVE;
    }

    public void updateStatus(UpdateReservationStatusCommand command) {
        this.status = ReservationStatus.valueOf(command.status());
    }
}
