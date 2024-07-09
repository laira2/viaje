package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="userId")
    private Users user;

    @Column(nullable = false)
    private int total_amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private LocalDateTime orderedAt;

    public enum OrderStatus{
        PENDING, PROCESSING, COMPLETED, CANCELLED
    }

    @PrePersist
    public void onCreate(){
        orderedAt = LocalDateTime.now();
        if (orderStatus== null) {
            orderStatus=OrderStatus.PENDING;
        }

    }




}
