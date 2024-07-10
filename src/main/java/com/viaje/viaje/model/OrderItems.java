package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderItemId;

//    @ManyToOne
//    @JoinColumn(name="cartId", nullable = false)
//    private Cart cart;

    @ManyToOne
    @JoinColumn(name="orderId")
    private Orders orders;

    @OneToOne
    @JoinColumn(name = "planId", nullable = false)
    private TravelPlans travelPlans;

    @Column(nullable = false)
    private Integer quantity;

    @PrePersist
    public void onCreate(){
        if (quantity == null) {
            quantity = 0;
        }
    }

}
