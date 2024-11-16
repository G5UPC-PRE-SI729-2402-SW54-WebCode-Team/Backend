package com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.valueObjects;

public enum ReservationStatus {
    ACTIVE(1, "Pending"),
    COMPLETED(1, "Confirmed");
    private final int id;
    private final String description;

    ReservationStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
