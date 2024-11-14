package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects;

/**
 * VehicleType
 * @summary VehicleType enumeration
 * Contains the types of vehicles that can be used in the system: Electric Bike, Electric Scooter, Electric Car
 * Possible values: ELECTRIC_BIKE, ELECTRIC_SCOOTER, ELECTRIC_CAR
 * @since 1.0
 */
public enum VehicleType {
    ELECTRIC_BIKE(1, "Electric Bike"),
    ELECTRIC_SCOOTER(2, "Electric Scooter"),
    ELECTRIC_CAR(3, "Electric Car");
    private final int id;
    private final String description;

    VehicleType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
