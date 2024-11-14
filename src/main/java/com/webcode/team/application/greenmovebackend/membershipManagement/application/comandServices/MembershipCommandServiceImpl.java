package com.webcode.team.application.greenmovebackend.membershipManagement.application.comandServices;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities.Membership;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.DeleteMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.UpdateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.services.MembershipCommandService;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.MembershipRepository;
import com.webcode.team.application.greenmovebackend.membershipManagement.infrastructure.persistence.jpa.respositories.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {
    private final MembershipRepository membershipRepository;
    private final TenantRepository tenantRepository;
    public MembershipCommandServiceImpl(MembershipRepository membershipRepository, TenantRepository tenantRepository) {
        this.membershipRepository = membershipRepository;
        this.tenantRepository = tenantRepository;
    }
    @Override
    public Optional<Membership> handle(CreateMembershipCommand command) {
        var membership = new Membership(command);
        var tenant = tenantRepository.findById(command.tenantId());
        membership.setTenant(tenant.get());
        var createdMembership = membershipRepository.save(membership);
        return Optional.of(createdMembership);
    }

    @Override
    public void handle(DeleteMembershipCommand command) {
        var membership = membershipRepository.findById(command.membershipId());
        if(!this.membershipRepository.existsById(command.membershipId())){
            throw new IllegalArgumentException("Membership not found");
        }try {
            membershipRepository.delete(membership.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Membership");
        }
    }

    @Override
    public Optional<Membership> handle(UpdateMembershipCommand command) {
        var membershipId = command.membershipId();
        if(!this.membershipRepository.existsById(membershipId)){
            throw new IllegalArgumentException("Membership not found");
        }
        var membershipToUpdate = membershipRepository.findById(membershipId).get();
        membershipToUpdate.updateInformation(command);

        try{
            var updatedMembership = membershipRepository.save(membershipToUpdate);
            return Optional.of(updatedMembership);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating Membership");
        }
    }
}
