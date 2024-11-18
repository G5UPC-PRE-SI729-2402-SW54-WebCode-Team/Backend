package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources;

import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.MembershipResource;

public record ReservationTenantResource(
        Long id,
        String name,
        String urlImage,
        String phone,
        String reservationTime
) {
}
