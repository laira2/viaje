package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class TravelPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String filePath;

    @Column(nullable = false)
    private Integer sold=0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanStatus status = PlanStatus.PENDING;

    public enum PlanStatus {
        PENDING, APPROVED, REJECTED, DELETED
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (sold == null) {
            sold = 0;
        }
        if (status== null) {
            status = PlanStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }





}
