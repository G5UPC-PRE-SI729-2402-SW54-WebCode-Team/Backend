package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources;

public record CreateMembershipResource(String type) {
    public CreateMembershipResource(String type) {
        this.type = type;
    }
}
