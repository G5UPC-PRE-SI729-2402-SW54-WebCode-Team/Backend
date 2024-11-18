package com.webcode.team.application.greenmovebackend.vehicleManagement.application.acl;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.acl.OwnerContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerContextFacadeImpl implements OwnerContextFacade {
    private final OwnerRepository ownerRepository;

    public OwnerContextFacadeImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<Owner> createOwner(String firstName, String lastName, String urlImage, String phone, String street, String number, String city, String postalCode, String country) {
        var owner = new Owner(firstName, lastName, urlImage, phone, street, number, city, postalCode, country);
        var createdOwner = ownerRepository.save(owner);
        return Optional.of(createdOwner);
    }
}
