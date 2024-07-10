package com.viaje.viaje.dto;

import com.viaje.viaje.model.PlanCertification;
import com.viaje.viaje.model.TravelPlans;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanCertificationDTO {

    private String certFileName;

    private String certFilePath;

    private List<MultipartFile> certImages;

}
