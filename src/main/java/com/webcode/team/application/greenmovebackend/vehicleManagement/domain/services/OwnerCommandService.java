package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteOwnerCommand;

import java.util.Optional;

/**
 * Owner command service: This service is responsible for handling owner commands.
 * @since 1.0
 */
public interface OwnerCommandService {
    /**
     * Handle create owner command
     * @param command the create owner command
     * @return the created owner
     */
    Optional<Owner> handle(CreateOwnerCommand command);
    /**
     * Handle delete owner command
     * @param command the delete owner command
     */
    void handle(DeleteOwnerCommand command);
}
