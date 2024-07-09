package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PlanCertification {

    @Id
    private Long planId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "planId")
    private TravelPlans travelPlans;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CertificationStatus certificationStatus;

    @Column(nullable = false)
    private LocalDateTime orderedAt;

    @ElementCollection
    @CollectionTable(name = "cert_images", joinColumns = @JoinColumn(name = "cert_id"))
    @Column(name = "image_path")
    private List<String> certImagePaths = new ArrayList<>();

    public enum CertificationStatus{
        PENDING, PROCESSING, COMPLETED, CANCELLED
    }

    @PrePersist
    public void onCreate(){
        orderedAt = LocalDateTime.now();
        if (certificationStatus== null) {
            certificationStatus= CertificationStatus.PENDING;
        }

    }
}
