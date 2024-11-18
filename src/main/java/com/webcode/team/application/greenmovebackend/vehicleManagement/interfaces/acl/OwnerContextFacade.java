package com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.acl;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;

import java.util.Optional;

public interface OwnerContextFacade {
    Optional<Owner> createOwner(String firstName, String lastName, String urlImage, String phone, String street, String number, String city, String postalCode, String country);
}
