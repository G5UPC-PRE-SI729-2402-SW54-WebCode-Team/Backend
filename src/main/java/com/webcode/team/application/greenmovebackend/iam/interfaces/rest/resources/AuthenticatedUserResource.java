package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
