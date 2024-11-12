package com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services;

import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.aggregates.Owner;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.model.queries.GetOwnerByIdQuery;

import java.util.Optional;

/**
 * Owner query service: interface for handling owner queries
 * <br>
 * It provides a method to handle the query to get an owner by id
 * <br>
 * GetOwnerByIdQuery: query to get an owner by id, receives the id of the owner
 * @version 1.0
 */
public interface OwnerQueryService {
    Optional<Owner> handle(GetOwnerByIdQuery query);
}
