package com.viaje.viaje.repository;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointTransactionRepository extends JpaRepository<PointTransaction,Long> {
    List<PointTransaction> findAllByUser_userId(Long userId);
}
