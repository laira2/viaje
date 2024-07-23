package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="planId")
    private TravelPlans travelPlans;

    @ManyToOne
    @JoinColumn(name="userId")
    private Users users;

    public Like(Users users, TravelPlans travelPlans) {
        this.users = users;
        this.travelPlans = travelPlans;
    }
}
