package com.viaje.viaje.repository;

import com.viaje.viaje.model.TossPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TossPaymentsRepository extends JpaRepository<TossPayments, Long>{
    TossPayments findByPaymentKey(String paymentKey);
}
