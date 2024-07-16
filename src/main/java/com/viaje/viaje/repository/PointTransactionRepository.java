package com.viaje.viaje.repository;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointTransactionRepository extends JpaRepository<PointTransaction,Long> {
    List<PointTransaction> findAllByUser_userId(Long userId);

}
