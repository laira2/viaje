package com.viaje.viaje.dto;

import com.viaje.viaje.model.PlanCertification;
import com.viaje.viaje.model.TravelPlans;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCertificationDTO {

    private String certFileName;

    private String certFilePath;

    private List<MultipartFile> certImages;

}
