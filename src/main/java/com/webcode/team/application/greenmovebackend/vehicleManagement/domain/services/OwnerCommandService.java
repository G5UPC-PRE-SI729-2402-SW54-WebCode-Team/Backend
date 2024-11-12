package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateOwnerCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteOwnerCommand;

import java.util.Optional;

/**
 * Owner command service: This service is responsible for handling owner commands.
 * <br>
 * It provides methods to handle create and delete owner commands.
 * <br>
 * Create owner command: This command is used to create a new owner, returning an optional owner.
 * <br>
 * Delete owner command: This command is used to delete an existing owner, returning nothing.
 * @since 1.0
 */
public interface OwnerCommandService {
    Optional<Owner> handle(CreateOwnerCommand command);
    void handle(DeleteOwnerCommand command);
}
