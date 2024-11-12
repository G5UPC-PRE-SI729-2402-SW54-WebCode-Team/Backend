package com.webcode.team.application.greenmovebackend.vehicleManagement.application;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetVehicleByIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {
    @Override
    public List<Vehicle> handle(GetAllVehiclesByOwnerIdQuery query) {
        return List.of();
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return Optional.empty();
    }
}
