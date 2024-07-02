package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String board_id;

    @OneToOne
    @JoinColumn(name="plan_id" )
    private TravelPlans travelPlans;

    @OneToOne
    @JoinColumn(name="user_id")
    private Users user;

    @Column(updatable = false, nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @Column(nullable = false)
    private Integer view_count =0 ;

    @Column(nullable = false)
    private Integer like_count = 0;


    private BoardStatus boardStatus = BoardStatus.PENDING;

    private enum BoardStatus {
        PENDING, APPROVE, REJECTED, DELETED
    }

}
