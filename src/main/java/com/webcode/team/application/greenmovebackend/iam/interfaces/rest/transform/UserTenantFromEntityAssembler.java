package com.webcode.team.application.greenmovebackend.iam.interfaces.rest.transform;

import com.webcode.team.application.greenmovebackend.iam.interfaces.rest.resources.UserTenantResource;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;

public class UserTenantFromEntityAssembler {
    public static UserTenantResource toResourceFromEntity(Tenant tenant) {
        return new UserTenantResource(
                tenant.getId(),
                tenant.getFullName(),
                tenant.getUrlImage(),
                tenant.getPhone()
        );
    }
}
