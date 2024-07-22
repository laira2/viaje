package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private long nights;

    @Column(nullable = false)
    private long days;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;

    @Column(nullable = false)
    private Integer totalBudget;

    @Column
    private Integer price;

    @Column(nullable = false)
    private Integer sold=0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "plan_images", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "image_path")
    private List<String> imagePaths = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanStatus status = PlanStatus.PENDING;

    @OneToOne(mappedBy = "travelPlans", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PlanCertification planCertification;

    public List<String> getCertImagePaths() {
        return planCertification != null ? planCertification.getCertImagePaths() : new ArrayList<>();
    }

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
        if (status == null) {
            status = PlanStatus.PENDING;
        }
        calculateDuration();
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
        calculateDuration();
    }



    private void calculateDuration() {
        if (startDate != null && endDate != null) {
            this.nights = ChronoUnit.DAYS.between(startDate, endDate);
            this.days = this.nights + 1;
            this.duration = this.nights + "박 " + this.days + "일";
        }
    }

    // Getter and Setter methods

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        calculateDuration();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        calculateDuration();
    }

}
