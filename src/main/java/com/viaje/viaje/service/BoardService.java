package com.viaje.viaje.service;

import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.TravelPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    public final TravelPlansRepository travelPlansRepository;
    @Autowired
    public BoardService(TravelPlansRepository travelPlansRepository) {
        this.travelPlansRepository = travelPlansRepository;
    }

    public List<TravelPlans> viewAllPlans (){
        List<TravelPlans> plan_list = travelPlansRepository.findAll();
        return plan_list;
    }
}
