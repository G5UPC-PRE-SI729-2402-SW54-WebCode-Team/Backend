package com.webcode.team.application.greenmovebackend.iam.application.internal.outboundservices.owners;

import com.webcode.team.application.greenmovebackend.iam.domain.model.commands.CreateUserOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.acl.OwnerContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalOwnerService {
    private final OwnerContextFacade ownerContextFacade;

    public ExternalOwnerService(OwnerContextFacade ownerContextFacade) {
        this.ownerContextFacade = ownerContextFacade;
    }

    public Optional<Owner> createUserOwner(CreateUserOwnerCommand command) {
        var owner = ownerContextFacade.createOwner(command.firstName(), command.lastName(), command.urlImage(), command.phone(), command.street(), command.number(), command.city(), command.postalCode(), command.country());
        if(owner.isEmpty()) {
            throw new RuntimeException("Owner not created");
        }
        return owner;
    }
}
