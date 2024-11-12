package com.webcode.team.application.greenmovebackend.vehicleManagement.application;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.OwnerCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerCommandServiceImpl implements OwnerCommandService {

    private final OwnerRepository ownerRepository;
    public OwnerCommandServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<Owner> handle(CreateOwnerCommand command) {
        var owner = new Owner(command);
        var createdOwner = ownerRepository.save(owner);
        return Optional.of(createdOwner);
    }

    @Override
    public void handle(DeleteOwnerCommand command) {
        var ownerId = command.ownerId();
        if(!this.ownerRepository.existsById(ownerId)) {
            throw new IllegalArgumentException("Owner with id " + ownerId + " does not exist");
        }
        try {
            this.ownerRepository.deleteById(ownerId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user: " + e.getMessage());
        }
    }
}
