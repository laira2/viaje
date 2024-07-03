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
    @JoinColumn(name="planId")
    private TravelPlans travelPlans;

    @JoinColumn(name="tagId")
    private Tags tags;
}


