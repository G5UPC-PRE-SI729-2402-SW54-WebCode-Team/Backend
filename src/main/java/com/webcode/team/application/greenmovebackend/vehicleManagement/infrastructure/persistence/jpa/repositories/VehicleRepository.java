package com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Vehicle repository
 * <br>
 * This interface is a repository for the Vehicle entity.
 * <br>
 * @since 1.0.0
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    /**
     * Find vehicles by owner id
     * @param ownerId the owner id
     * @return a list of vehicles
     */
    List<Vehicle> findByOwnerId(Long ownerId);
}
