package com.viaje.viaje.service;


import com.viaje.viaje.dto.PointTransactionDTO;
import com.viaje.viaje.model.PointTransaction;
import com.viaje.viaje.model.PointTransaction.TransactionStatus;
import com.viaje.viaje.model.PointTransaction.TransactionType;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.PointTransactionRepository;
import com.viaje.viaje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PointTransactionService {

    private final PointTransactionRepository pointTransactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public PointTransactionService(PointTransactionRepository transactionRepository, UserRepository userRepository) {
        this.pointTransactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PointTransactionDTO chargePoints(Long userId, Integer chargeAmount, Integer chargePoint) {
        // 사용자 정보 조회
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));

        // 포인트 거래 생성
        PointTransaction transaction = PointTransaction.builder()
                .user(user)
                .chargeAmount(chargeAmount)
                .chargePoint(chargePoint)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionType(TransactionType.CREDIT)
                .build();

        // 엔티티 저장
        transaction = pointTransactionRepository.save(transaction);

        // DTO 반환
        return PointTransactionDTO.builder()
                .pointTransactionId(transaction.getPointTransactionId())
                .userId(transaction.getUser().getUserId())
                .chargeAmount(transaction.getChargeAmount())
                .chargePoint(transaction.getChargePoint())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .transactionStatus(transaction.getTransactionStatus().toString())
                .transactionType(transaction.getTransactionType().toString())
                .build();
    }

}
