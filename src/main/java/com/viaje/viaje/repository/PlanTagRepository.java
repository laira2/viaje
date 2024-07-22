package com.viaje.viaje.repository;

import com.viaje.viaje.model.PlanTag;
import com.viaje.viaje.model.Tags;
import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanTagRepository extends JpaRepository<PlanTag, Long> {
    List<PlanTag> findAllByTags(Tags tag);

    List<PlanTag> findAllByTravelPlans(TravelPlans travelPlans);
}