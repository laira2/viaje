package com.viaje.viaje.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TossPaymentsDTO {
    private String paymentKey;
    private String type;
    private String orderId;
    private String orderName;
    private String status;
    private Long totalAmount;
    private String method;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
}
