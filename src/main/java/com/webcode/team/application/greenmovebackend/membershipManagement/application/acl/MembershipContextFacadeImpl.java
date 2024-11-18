package com.webcode.team.application.greenmovebackend.membershipManagement.application.acl;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.MembershipRepository;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.acl.MembershipContextFacade;
import com.webcode.team.application.greenmovebackend.membershipManagement.interfaces.rest.resources.CreateMembershipResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipContextFacadeImpl implements MembershipContextFacade {
    private final MembershipRepository membershipRepository;
    private final TenantRepository tenantRepository;
    public MembershipContextFacadeImpl(MembershipRepository membershipRepository, TenantRepository tenantRepository) {
        this.membershipRepository = membershipRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Optional<Membership> createMembership(CreateMembershipCommand command) {
        var membership = new Membership(command);
        var tenant = tenantRepository.findById(command.tenantId());
        membership.setTenant(tenant.get());
        var createdMembership = membershipRepository.save(membership);
        return Optional.of(createdMembership);
    }
}
