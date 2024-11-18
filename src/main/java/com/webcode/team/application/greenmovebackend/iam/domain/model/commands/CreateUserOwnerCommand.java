package com.webcode.team.application.greenmovebackend.iam.domain.model.commands;

public record CreateUserOwnerCommand(Long userId,
                                     String firstName,
                                     String lastName,
                                     String urlImage,
                                     String phone,
                                     String street,
                                     String number,
                                     String city,
                                     String postalCode,
                                     String country) {
}
