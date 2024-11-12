package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.PersonName;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.StreetAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Owner aggregate root entity.
 *
 * @summary This class represents the Owner aggregate root entity.
 * It contains the owner's name, image URL, phone, address, rating, and vehicles.
 * @see PersonName
 * @see Vehicle
 * @see AuditableAbstractAggregateRoot
 * @since 1.0
 */

@Entity
public class Owner extends AuditableAbstractAggregateRoot<Owner> {
    @Embedded
    private PersonName name;
    private String urlImage;
    @Size(max = 9)
    private String phone;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country")),
    })
    private StreetAddress address;
    private Float rating;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    public Owner() {
        // Required by JPA
        rating = 0.0f;
    }

    public Owner(CreateOwnerCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.urlImage = command.urlImage();
        this.phone = command.phone();
        this.address = new StreetAddress(command.street(), command.number(), command.city(), command.postalCode(), command.country());
        this.rating = 0.0f;
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getStreetAddress() {
        return address.getStreetAddress();
    }
}
