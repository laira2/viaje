package com.viaje.viaje.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelPlansDTO {

    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String nation;
    private String title;
    private String detail;
    private Integer totalBudget;
    private String fileName;
    private String filePath;
    private Long planId;



}
