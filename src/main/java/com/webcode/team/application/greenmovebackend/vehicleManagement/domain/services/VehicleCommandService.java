package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.CreateVehicleCommand;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.commands.DeleteVehicleCommand;

import java.util.Optional;
/**
 * Vehicle command service: interface for handling vehicle commands
 * <br>
 * It provides methods to handle vehicle commands
 * <br>
 * CreateVehicleCommand: command to create a vehicle, returns an optional vehicle
 * <br>
 * DeleteVehicleCommand: command to delete a vehicle, returns nothing
 * @version 1.0
 */
public interface VehicleCommandService {
    Optional<Vehicle> handle(CreateVehicleCommand command);
    void handle(DeleteVehicleCommand command);
}
