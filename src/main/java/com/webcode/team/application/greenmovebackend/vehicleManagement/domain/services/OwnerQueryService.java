package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetOwnerByIdQuery;

import java.util.Optional;

/**
 * Owner query service: interface for handling owner queries
 * @version 1.0
 */
public interface OwnerQueryService {
    /**
     * Handle the query to get an owner by id
     * @param query the query to get an owner by id
     * @return the owner if found, otherwise empty
     */
    Optional<Owner> handle(GetOwnerByIdQuery query);
}
