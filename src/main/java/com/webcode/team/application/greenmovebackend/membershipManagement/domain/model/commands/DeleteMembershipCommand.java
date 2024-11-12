package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands;

/**
 * DeleteMembershipCommand: Command to delete a membership.
 * @param membershipId: The membership id.
 */
public record DeleteMembershipCommand(Long membershipId) {
}
