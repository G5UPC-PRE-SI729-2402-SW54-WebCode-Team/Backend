package com.webcode.team.application.greenmovebackend.reservationManagement.application.internal.outboundServices.acl;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.interfaces.acl.VehicleContextFacade;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ExternalVehicleService {
    private final VehicleContextFacade vehicleContextFacade;

    public ExternalVehicleService(VehicleContextFacade vehicleContextFacade) {
        this.vehicleContextFacade = vehicleContextFacade;
    }

    public void updateVehicleStatus(Long vehicleId, String status) {
        vehicleContextFacade.updateVehicleStatus(vehicleId, status);
    }

    public boolean isVehicleAvailable(Long vehicleId) {
        return vehicleContextFacade.isVehicleAvailable(vehicleId);
    }

    public boolean isOwnerOfVehicle(Long vehicleId, Long ownerId) {
        return vehicleContextFacade.isOwnerOfVehicle(vehicleId, ownerId);
    }
    public Optional<Vehicle> fetchVehicleById(Long vehicleId) {
        return vehicleContextFacade.getVehicleById(vehicleId);
    }
}
