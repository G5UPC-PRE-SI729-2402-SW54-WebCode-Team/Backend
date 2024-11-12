package com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.aggregates;

import com.webcode.team.application.greenmovebackend.membershipManagement.domain.model.valueobjects.PersonName;
import com.webcode.team.application.greenmovebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * This class represents the Tenant aggregate root.
 * @summary This class represents the Tenant aggregate root.
 * It contains the tenant's name, image, and phone number.
 * @see PersonName
 * @see AuditableAbstractAggregateRoot
 * @see Membership
 * @since 1.0
 */
@Getter
@Entity
public class Tenant extends AuditableAbstractAggregateRoot<Tenant> {
    @Embedded
    private PersonName name;
    private String urlImage;
    @Size(max = 9)
    private String phone;

    @OneToOne (mappedBy = "tenant", orphanRemoval = true)
    private Membership membership;
    public Tenant(){
        // Required by JPA
    }
}