package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;

import java.util.Date;

public record MembershipResource(
        Long id,
        String type,
        TenantResource tenant,
        Date startDate,
        Date endDate
) {
}
