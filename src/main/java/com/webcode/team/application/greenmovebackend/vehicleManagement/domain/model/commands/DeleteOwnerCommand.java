package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

/**
 * DeleteOwnerCommand
 * @summary this record represents the command to delete an owner
 * @param ownerId the owner id
 * @since 1.0
 */
public record DeleteOwnerCommand(Long ownerId) {
}
