package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.valueobjects;

/**
 * MembershipType enum
 * @summary Enum for MembershipType
 * It contains the membership types
 * It could be DEFAULT, BASIC, INTERMEDIATE, ADVANCED
 * @since 1.0
 */
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
