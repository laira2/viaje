package com.viaje.viaje.service;

import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.TravelPlansRepository;

import java.util.List;

public class BoardService {
    public final TravelPlansRepository travelPlansRepository;

    public BoardService(TravelPlansRepository travelPlansRepository) {
        this.travelPlansRepository = travelPlansRepository;
    }

    public List<TravelPlans> viewAllPlans (){
        List<TravelPlans> plan_list = travelPlansRepository.findAll();
        return plan_list;
    }
}
