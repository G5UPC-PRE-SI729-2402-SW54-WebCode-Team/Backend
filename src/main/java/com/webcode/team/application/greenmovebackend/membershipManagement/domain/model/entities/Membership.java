package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.entities;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates.Tenant;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.CreateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.commands.UpdateMembershipCommand;
import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.valueobjects.MembershipType;
import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.webcode.team.application.greenmovebackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 * Membership aggregate root entity
 * @summary Membership aggregate root entity
 * It contains the membership type, start date, end date, tenant
 * @see AuditableAbstractAggregateRoot
 * @see MembershipType
 * @since 1.0
 */
@Entity
@Getter
public class Membership extends AuditableModel {
    @Enumerated(EnumType.STRING)
    private MembershipType type;
    private Date startDate;
    private Date endDate;
    @Setter
    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    public Membership(){
        this.type = MembershipType.DEFAULT;
        this.startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startDate);
        calendar.add(Calendar.YEAR, 1);
        this.endDate = calendar.getTime();
    }

    public Membership(CreateMembershipCommand command){
        if(command.type() == null) {
            this.type = MembershipType.DEFAULT;
        }else {
            this.type = MembershipType.valueOf(command.type());
        }
        this.startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startDate);
        calendar.add(Calendar.YEAR, 1);
        this.endDate = calendar.getTime();
    }

    public void updateInformation(UpdateMembershipCommand command){
        if(command.type() == null) {
            this.type = MembershipType.DEFAULT;
        }else {
            this.type = MembershipType.valueOf(command.type());
        }
        this.startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startDate);
        calendar.add(Calendar.YEAR, 1);
        this.endDate = calendar.getTime();
    }
}
