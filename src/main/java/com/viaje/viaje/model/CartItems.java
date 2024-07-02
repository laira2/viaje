package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"plan_id", "cart_id"}))
public class CartItems {

    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "plan_id")
    public TravelPlans travelPlans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private LocalDateTime added_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @Column(nullable = false)
    private Integer quantity = 1;

    @PrePersist
    protected void onAdded() {
        added_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }

}

