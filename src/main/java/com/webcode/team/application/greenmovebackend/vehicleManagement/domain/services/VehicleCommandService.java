package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteVehicleCommand;

import java.util.Optional;
/**
 * Vehicle command service: interface for handling vehicle commands
 * @version 1.0
 */
public interface VehicleCommandService {
    /**
     * Handle create vehicle command
     * @param command the create vehicle command
     * @return the created vehicle
     */
    Optional<Vehicle> handle(CreateVehicleCommand command);
    /**
     * Handle delete vehicle command
     * @param command the delete vehicle command
     */
    void handle(DeleteVehicleCommand command);
}
