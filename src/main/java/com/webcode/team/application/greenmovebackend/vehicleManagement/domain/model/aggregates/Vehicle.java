package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.VehicleStatus;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.VehicleType;
import jakarta.persistence.*;

/**
 * Vehicle entity
 * @summary This entity represents the vehicle aggregate root entity.
 * It contains the name, image URL, status, type, and owner.
 * @since 1.0
 */
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {
    private String name;
    private String urlImage;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Vehicle() {
        // Required by JPA
    }

}
