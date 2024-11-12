package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands;

/**
 * CreateOwnerCommand
 * @summary
 * This command is used to create a new owner.
 * @param firstName the first name of the owner.
 * @param lastName the last name of the owner.
 * @param urlImage the url of the image of the owner.
 * @param phone the phone number of the owner.
 * @param street the street of the owner.
 * @param number the number of the owner.
 * @param city the city of the owner.
 * @param postalCode the postal code of the owner.
 * @param country the country of the owner.
 * @since 1.0
 */
public record CreateOwnerCommand(
        String firstName,
        String lastName,
        String urlImage,
        String phone,
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {
}
