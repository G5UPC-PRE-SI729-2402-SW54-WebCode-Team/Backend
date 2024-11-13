package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

/**
 * UpdateMembershipCommand: Command to update a membership
 * @param membershipId
 */
public record UpdateMembershipCommand(Long membershipId, String type) {
}
