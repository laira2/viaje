package com.viaje.viaje.repository;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointTransactionRepository extends JpaRepository<PointTransaction,Long> {

}
