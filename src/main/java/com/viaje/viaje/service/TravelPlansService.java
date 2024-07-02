package com.viaje.viaje.service;

import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.TravelPlansRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelPlansService {

    private final TravelPlansRepository travelPlansRepository;

    public TravelPlansService(TravelPlansRepository travelPlansRepository) {
        this.travelPlansRepository = travelPlansRepository;
    }

    public Long postPlan(TravelPlans travelPlans){

        return travelPlans.getPlanId();

    }
}
