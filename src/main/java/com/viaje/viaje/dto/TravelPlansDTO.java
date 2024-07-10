package com.viaje.viaje.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
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
