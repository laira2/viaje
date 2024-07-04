package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlanTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="planId")
    private TravelPlans travelPlans;

    @ManyToOne
    @JoinColumn(name="tagId")
    private Tags tags;
}


