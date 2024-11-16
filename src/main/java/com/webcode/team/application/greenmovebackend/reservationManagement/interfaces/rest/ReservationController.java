package com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.TenantQueryService;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.DeleteReservationCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.commands.UpdateReservationStatusCommand;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetActiveReservationByTenantId;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetAllReservationsByOwnerIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetAllReservationsByTenantIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.model.queries.GetReservationByIdQuery;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.services.ReservationCommandService;
import com.webcode.team.application.greenmovebackend.reservationManagement.domain.services.ReservationQueryService;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.CreateReservationResource;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.resources.ReservationResource;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.webcode.team.application.greenmovebackend.reservationManagement.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.OwnerQueryService;
import com.webcode.team.application.greenmovebackend.vehicleManagement.domain.services.VehicleQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/reservations")
@Tag(name = "Reservations", description = "Reservations endpoints")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
public class ReservationController {
    private final ReservationQueryService reservationQueryService;
    private final ReservationCommandService reservationCommandService;

    public ReservationController(ReservationQueryService reservationQueryService, ReservationCommandService reservationCommandService) {
        this.reservationQueryService = reservationQueryService;
        this.reservationCommandService = reservationCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new reservation", description = "Create a new reservation")
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservation = reservationCommandService.handle(createReservationCommand);

        if(reservation.isEmpty()) return ResponseEntity.badRequest().build();

        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());

        return ResponseEntity.ok(reservationResource);
    }

    @GetMapping("/{reservationId}")
    @Operation(summary = "Get a reservation by id", description = "Get a reservation by id")
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable Long reservationId) {
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = this.reservationQueryService.handle(getReservationByIdQuery);

        if(reservation.isEmpty()) return ResponseEntity.notFound().build();

        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get all reservations by owner id", description = "Get all reservations by owner id")
    public ResponseEntity<List<ReservationResource>> getAllReservationsByOwnerId(@PathVariable Long ownerId) {
        var getReservationsByOwnerIdQuery = new GetAllReservationsByOwnerIdQuery(ownerId);
        var reservations = reservationQueryService.handle(getReservationsByOwnerIdQuery);
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("/tenant/{tenantId}")
    @Operation(summary = "Get all reservations by tenant id", description = "Get all reservations by tenant id")
    public ResponseEntity<List<ReservationResource>> getAllReservationsByTenantId(@PathVariable Long tenantId) {
        var getReservationsByTenantIdQuery = new GetAllReservationsByTenantIdQuery(tenantId);
        var reservations = reservationQueryService.handle(getReservationsByTenantIdQuery);
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("/tenant/{tenantId}/active")
    @Operation(summary = "Get active reservation by tenant id", description = "Get active reservation by tenant id")
    public ResponseEntity<ReservationResource> getActiveReservationByTenantId(@PathVariable Long tenantId) {
        var getActiveReservationByTenantIdQuery = new GetActiveReservationByTenantId(tenantId);
        var reservation = reservationQueryService.handle(getActiveReservationByTenantIdQuery);
        if(reservation.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @DeleteMapping("/{reservationId}")
    @Operation(summary = "Delete a reservation by id", description = "Delete a reservation by id")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        var deleteReservationCommand = new DeleteReservationCommand(reservationId);
        this.reservationCommandService.handle(deleteReservationCommand);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{reservationId}/status/{status}")
    @Operation(summary = "Update reservation status", description = "Update reservation status")
    public ResponseEntity<ReservationResource> updateReservationStatus(@PathVariable Long reservationId, @PathVariable String status) {
        var updateReservationStatusCommand = new UpdateReservationStatusCommand(reservationId, status);
        var reservation = this.reservationCommandService.handle(updateReservationStatusCommand);
        if(reservation.isEmpty()) return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }
}
