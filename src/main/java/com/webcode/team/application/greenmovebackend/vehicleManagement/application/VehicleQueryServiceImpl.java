package com.webcode.team.application.greenmovebackend.vehicleManagement.application;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetAllVehiclesByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetVehicleByIdQuery;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleQueryService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {

    private final VehicleRepository vehicleRepository;
    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public List<Vehicle> handle(GetAllVehiclesByOwnerIdQuery query) {
        return this.vehicleRepository.findByOwnerId(query.ownerId());
    }
    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return this.vehicleRepository.findById(query.vehicleId());
    }
}
