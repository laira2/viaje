package com.viaje.viaje.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointTransactionDTO {
    private Long pointTransactionId;  // 트랜잭션 ID
    private Long userId;  // 사용자 ID
    private Integer chargeAmount;  // 충전 금액
    private Integer chargePoint;  // 충전 포인트
    private LocalDateTime createdAt;  // 생성 일시
    private LocalDateTime updatedAt;  // 업데이트 일시
    private String transactionStatus;  // 트랜잭션 상태
    private String transactionType;  // 트랜잭션 타입

}
