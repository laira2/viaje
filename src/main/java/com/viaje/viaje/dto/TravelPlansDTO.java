package com.viaje.viaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelPlansDTO {

    private Long userId;
    private String nation;
    private String title;
    private String detail;
    private String fileName;
    private String filePath;
    private Long planId;


}
