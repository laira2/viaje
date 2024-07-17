package com.viaje.viaje.repository;

import com.viaje.viaje.model.PlanDetail;
import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanDetailRepository extends JpaRepository<PlanDetail, Long> {

    List<PlanDetail> findAllByTravelPlanOrderByPlanDateAscPlanTimeAsc(TravelPlans travelPlan);

    void deleteAllByTravelPlan(TravelPlans travelPlan);
}
