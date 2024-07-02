package com.viaje.viaje.controller;

import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.service.TravelPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelPlansController {

    @Autowired
    public TravelPlansController(){

    }

    @GetMapping("/plans/new")
    public String createPlanForm(){

        return "/plans/TravelPlansDTO";
    }
    @PostMapping("/plans/new")
    public String createPlan(TravelPlansDTO tpDTO){
        TravelPlans travelPlans = new TravelPlans();
        travelPlans.setNation(tpDTO.getNation());
        travelPlans.setTitle(tpDTO.getTitle());
        travelPlans.setDetail(tpDTO.getDetail());
        travelPlans.setFileName(tpDTO.getFileName());
        travelPlans.setFilePath(tpDTO.getFilePath());
//        TravelPlansService.postPlan(travelPlans);

        return "redirect:/";
    }

}
