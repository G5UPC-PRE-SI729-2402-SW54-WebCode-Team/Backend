package com.webcode.team.application.greenmovebackend.membershipManagement.application.queryServices;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.queries.GetMembershipByTenantIdQuery;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.MembershipQueryService;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {
    private final MembershipRepository membershipRepository;
    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }
    @Override
    public Optional<Membership> handle(GetMembershipByTenantIdQuery query) {
        return this.membershipRepository.findByTenantId(query.tenantId());
    }
}
