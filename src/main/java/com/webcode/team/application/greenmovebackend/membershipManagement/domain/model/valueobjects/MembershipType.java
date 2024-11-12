package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.valueobjects;

public enum MembershipType {
    DEFAULT(0, "Default"),
    BASIC(1, "Basic"),
    INTERMEDIATE(2, "Intermediate"),
    ADVANCED(3, "Advanced");
    private final int id;
    private final String description;

    MembershipType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
