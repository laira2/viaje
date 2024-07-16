package com.viaje.viaje.repository;

import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlansRepository extends JpaRepository<TravelPlans, Long> {

    List<TravelPlans> findAllByTitle(String title);
    List<TravelPlans> findAllByUser_userId(Long userId);
    List<TravelPlans> findAllByNation(String nation);
    List<TravelPlans> findByStatus(TravelPlans.PlanStatus status);

}