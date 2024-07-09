package com.viaje.viaje.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

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
    private List<MultipartFile> planImages;
}
