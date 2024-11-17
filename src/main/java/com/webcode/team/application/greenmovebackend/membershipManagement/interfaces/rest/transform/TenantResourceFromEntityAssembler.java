package com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.TenantResource;

public class TenantResourceFromEntityAssembler {
    public static TenantResource toResourceFromEntity(Tenant tenant){
        return new TenantResource(
                tenant.getId(),
                tenant.getName().getFullName(),
                tenant.getUrlImage(),
                tenant.getPhone(),
                MembershipResourceFromEntityAssembler.toResourceFromEntity(tenant.getMembership())
        );
    }
}
