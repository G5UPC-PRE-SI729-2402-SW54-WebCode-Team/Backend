package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.valueobjects.MembershipType;
import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

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
        this.startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startDate);
        calendar.add(Calendar.YEAR, 1);
        this.endDate = calendar.getTime();
    }
}
