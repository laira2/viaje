package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity // 테이블 이름을 명시적으로 지정
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planDetailId;

    @ManyToOne(fetch = FetchType.LAZY)  // LAZY 로딩 추천
    @JoinColumn(name = "planId")  // 데이터베이스 컬럼명 규칙에 맞춤
    private TravelPlans travelPlan;  // 단수형으로 변경

    @Column(name = "planDate")
    private LocalDate planDate;

    @Column(name = "planTime")
    private LocalTime planTime;

    @Column(length = 255)
    private String activity;

    @Column(length = 255)
    private String description;
}
