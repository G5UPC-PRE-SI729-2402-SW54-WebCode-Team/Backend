package com.webcode.team.application.greenmovebackend.vehicleManagement.infrastructure.persistence.jpa.repositories;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Vehicle;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.VehicleStatus;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.valueobjects.VehicleType;
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

    /**
     * Find vehicles by status
     * @param status the status of the vehicle
     * @return a list of vehicles
     */
    List<Vehicle> findByStatus(VehicleStatus status);

    /**
     * Find vehicles by type
     * @param type the type of the vehicle
     * @return a list of vehicles
     */
    List<Vehicle> findByType(VehicleType type);
}
