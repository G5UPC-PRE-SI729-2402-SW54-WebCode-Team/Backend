package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources;

import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.TenantResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.OwnerResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.rest.resources.VehicleResource;

public record ReservationResource(
        Long id,
        String reservationCode,
        String startDate,
        String endDate,
        String latitude,
        String longitude,
        OwnerResource owner,
        TenantResource tenant,
        ReservationVehicleResource vehicle
) {
}
