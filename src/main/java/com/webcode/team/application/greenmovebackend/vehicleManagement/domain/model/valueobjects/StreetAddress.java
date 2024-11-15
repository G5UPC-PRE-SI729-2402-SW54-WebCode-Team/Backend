package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

/**
 * StreetAddress value object
 * @summary StreetAddress value object for the vehicle management bounded context
 * this record is used to represent the street address of a vehicle
 * @since 1.0
 *
 */

@Embeddable
public record StreetAddress(
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {
    public StreetAddress(){
        this("","","","","");
    }

    public StreetAddress(String street, String city, String postalCode, String country) {
        this(street, null, city, postalCode, country);
    }

    public String getStreetAddress(){
        return String.format("%s %s %s %s %s", street, number, city, postalCode, country);
    }

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required");
        }

        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code is required");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country is required");
        }
    }
}