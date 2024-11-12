package com.webcode.team.application.greenmovebackend.vehicleManagement.application;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleCommandService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final OwnerRepository ownerRepository;
    private final VehicleRepository vehicleRepository;
    public VehicleCommandServiceImpl(OwnerRepository ownerRepository, VehicleRepository vehicleRepository) {
        this.ownerRepository = ownerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        var vehicle = new Vehicle(command);
        var owner = ownerRepository.findById(command.ownerId());
        vehicle.setOwner(owner.get());
        var createdVehicle = vehicleRepository.save(vehicle);
        return Optional.of(createdVehicle);
    }

    @Override
    public void handle(DeleteVehicleCommand command) {
        var vehicle = vehicleRepository.findById(command.vehicleId());
        if(!this.vehicleRepository.existsById(command.vehicleId())) {
            throw new IllegalArgumentException("Vehicle with id " + command.vehicleId() + " does not exist");
        }
        try {
            vehicleRepository.delete(vehicle.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting vehicle: " + e.getMessage());
        }
    }
}
