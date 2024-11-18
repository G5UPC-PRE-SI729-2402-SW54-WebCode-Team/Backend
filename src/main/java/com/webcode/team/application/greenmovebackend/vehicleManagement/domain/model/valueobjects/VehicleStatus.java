package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects;

/**
 * Vehicle status enumeration
 * @summary Vehicle status enumeration
 * Contains the possible statuses of a vehicle: available, on trip, on maintenance
 * Possible values: AVAILABLE, ON_TRIP, ON_MAINTENANCE
 * @since 1.0
 */

public enum VehicleStatus {
    AVAILABLE(1, "Available"),
    ON_TRIP(2, "On trip"),
    ON_MAINTENANCE(3, "On maintenance");
    private final int id;
    private final String description;

    VehicleStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
