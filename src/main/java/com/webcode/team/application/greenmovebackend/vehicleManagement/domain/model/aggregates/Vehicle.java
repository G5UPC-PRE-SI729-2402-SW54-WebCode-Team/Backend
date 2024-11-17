package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.UpdateVehicleStatusCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.VehicleStatus;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle entity
 * @summary This entity represents the vehicle aggregate root entity.
 * It contains the name, image URL, status, type, and owner.
 * @since 1.0
 */
@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {
    private String name;
    private String urlImage;
    private String latitude;
    private String longitude;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Vehicle() {
        // Required by JPA
    }

    public Vehicle(CreateVehicleCommand command) {
        this.name = command.name();
        this.latitude = command.latitude();
        this.longitude = command.longitude();
        this.urlImage = command.urlImage();
        this.status = VehicleStatus.AVAILABLE;
        this.type = VehicleType.valueOf(command.type().toUpperCase());
    }

    public void updateInformation(UpdateVehicleStatusCommand command) {
        this.status = VehicleStatus.valueOf(command.status().toUpperCase());
    }

}
