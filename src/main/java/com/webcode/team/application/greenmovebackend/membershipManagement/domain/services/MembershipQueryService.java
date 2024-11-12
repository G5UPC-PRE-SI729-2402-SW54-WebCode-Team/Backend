package com.webcode.team.application.greenmovebackend.membershipManagement.domain.services;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetMembershipByTenantIdQuery;

import java.util.Optional;

public interface MembershipQueryService {
    Optional<Membership> handle(GetMembershipByTenantIdQuery query);
}
