package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlanCert {
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "planId")
    private TravelPlans travelPlans;

    private String certification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanCertification status = PlanCertification.PENDING;

    public enum PlanCertification {
        PENDING, APPROVED, REJECTED, DELETED
    }
}