package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.ReservationVehicleResource;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;

public class ReservationVehicleFromEntityAssembler {
    public static ReservationVehicleResource toResourceFromEntity(Vehicle vehicle) {
        return new ReservationVehicleResource(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getUrlImage(),
                vehicle.getStatus().name(),
                vehicle.getType().name(),
                vehicle.getLongitude(),
                vehicle.getLatitude(),
                vehicle.getSpeed()
        );
    }
}
