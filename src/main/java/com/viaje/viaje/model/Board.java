package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @OneToOne










































































































    @JoinColumn(name="planId" )
    private TravelPlans travelPlans;

    @OneToOne
    @JoinColumn(name="userId")
    private Users user;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer viewCount =0 ;

    @Column(nullable = false)
    private Integer likeCount = 0;


    private BoardStatus boardStatus = BoardStatus.PENDING;

    private enum BoardStatus {
        PENDING, APPROVE, REJECTED, DELETED
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (viewCount == null) {
            viewCount = 0;
        }
        if (likeCount == null) {
            likeCount = 0;
        }
        if (boardStatus== null) {
            boardStatus = BoardStatus.PENDING;
        }
    }

}
