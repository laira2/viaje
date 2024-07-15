package com.viaje.viaje.dto;

import com.viaje.viaje.model.TravelPlans;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanDetailDTO {
        private TravelPlans travelPlan;  // 단수형으로 변경

        private LocalDate planDate;

        private LocalTime planTime;

        private String activity;

        private String description;
}
