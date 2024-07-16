package com.viaje.viaje.repository;

import com.viaje.viaje.model.TravelPlans;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlansRepository extends JpaRepository<TravelPlans, Long> {

    List<TravelPlans> findAllByTitle(String title);
    List<TravelPlans> findAllByUser_userId(Long userId);
    List<TravelPlans> findAllByNation(String nation);
    List<TravelPlans> findByStatus(TravelPlans.PlanStatus status);

    @Modifying
    @Transactional
    @Query("UPDATE TravelPlans t SET t.status = :status WHERE t.id = :id")
    void updatePlanStatus(@Param("id") Long id, @Param("status") TravelPlans.PlanStatus status);

}