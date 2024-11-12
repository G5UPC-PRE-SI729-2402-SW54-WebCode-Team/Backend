package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.valueobjects.MembershipType;
import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

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
public class Membership extends AuditableAbstractAggregateRoot<Membership> {
    @Enumerated(EnumType.STRING)
    private MembershipType type;
    private Date startDate;
    private Date endDate;
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
}
