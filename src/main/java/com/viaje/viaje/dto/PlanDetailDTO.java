package com.viaje.viaje.dto;

import com.viaje.viaje.model.TravelPlans;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDetailDTO {

    private TravelPlans travelPlan;  // 단수형으로 변경

    private LocalDate planDate;

    private LocalTime planTime;

    private String activity;

    private String description;
}
