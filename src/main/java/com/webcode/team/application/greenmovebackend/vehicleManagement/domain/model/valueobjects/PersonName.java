package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Person name value object.
 *
 * @summary This class represents the Person name value object.
 * It contains the first name and last name.
 * @since 1.0
 */
@Embeddable
public record PersonName (String firstName, String lastName) {
    public PersonName() { this(null, null); }

    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
    }

    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }
}