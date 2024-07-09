package com.viaje.viaje.repository;

import com.viaje.viaje.model.PlanCertification;
import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCertificationRepository extends JpaRepository<PlanCertification, Long> {
}
