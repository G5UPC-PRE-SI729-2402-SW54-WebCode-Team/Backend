package com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
