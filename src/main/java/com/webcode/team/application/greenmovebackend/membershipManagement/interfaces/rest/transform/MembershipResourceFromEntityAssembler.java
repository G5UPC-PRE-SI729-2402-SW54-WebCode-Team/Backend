package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.MembershipResource;

public class MembershipResourceFromEntityAssembler {
    public static MembershipResource toResourceFromEntity(Membership membership){
        return new MembershipResource(
                membership.getId(),
                membership.getType().name(),
                TenantResourceFromEntityAssembler.toResourceFromEntity(membership.getTenant()),
                membership.getStartDate(),
                membership.getEndDate()
        );
    }
}
