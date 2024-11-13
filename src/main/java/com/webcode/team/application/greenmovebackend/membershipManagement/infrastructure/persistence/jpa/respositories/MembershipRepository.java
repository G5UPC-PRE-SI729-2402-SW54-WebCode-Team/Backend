package com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByTenantId(Long ownerId);
}
