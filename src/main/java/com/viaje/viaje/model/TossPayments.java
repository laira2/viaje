package com.viaje.viaje.model;

import com.viaje.viaje.dto.TossPaymentsDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor  // 기본 생성자를 자동으로 생성해주는 Lombok 어노테이션
@AllArgsConstructor  // 모든 필드를 포함하는 생성자를 자동으로 생성해주는 Lombok 어노테이션
@Builder  // 빌더 패턴을 사용할 수 있게 해주는 Lombok 어노테이션
@Getter  // 모든 필드의 Getter 메서드를 자동으로 생성해주는 Lombok 어노테이션
@Setter
public class TossPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentKey; // 결제의 키 값, 최대 길이는 200자
    private String type; // 결제 타입 정보, NORMAL(일반결제), BILLING(자동결제), BRANDPAY(브랜드페이)
    private String orderId; // 주문번호, 최소 길이는 6자, 최대 길이는 64자
    private String orderName; // 구매상품 예)생수 외 1건 같은 형식, 최대 100자
    private String status; // 결제 처리 상태
    private Long totalAmount; // 총 결제 금액, 결제 상태가 변해도 최초에 결제된 결제 금액으로 유지
    private String method; // 결제수단, 카드, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서문화상품권, 게임문화상품권
    private LocalDateTime requestedAt; // 결제가 일어난 날짜와 시간 정보
    private LocalDateTime approvedAt; // 결제 승인이 일어난 날짜와 시간 정보

    public static TossPayments fromDTO(TossPaymentsDTO dto) {
        return TossPayments.builder()
                .paymentKey(dto.getPaymentKey())
                .type(dto.getType())
                .orderId(dto.getOrderId())
                .orderName(dto.getOrderName())
                .status(dto.getStatus())
                .totalAmount(dto.getTotalAmount())
                .method(dto.getMethod())
                .requestedAt(dto.getRequestedAt())
                .approvedAt(dto.getApprovedAt())
                .build();
    }

    public TossPaymentsDTO toDTO() {
        return TossPaymentsDTO.builder()
                .paymentKey(this.paymentKey)
                .type(this.type)
                .orderId(this.orderId)
                .orderName(this.orderName)
                .status(this.status)
                .totalAmount(this.totalAmount)
                .method(this.method)
                .requestedAt(this.requestedAt)
                .approvedAt(this.approvedAt)
                .build();
    }

}
