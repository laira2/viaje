package com.viaje.viaje.controller;

import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.service.TravelPlansService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelPlansController {

    public final TravelPlansService travelPlansService;

    @Autowired
    public TravelPlansController(TravelPlansService travelPlansService){

        this.travelPlansService = travelPlansService;
    }
    @GetMapping("/plans/new")
    public String createPlanForm(){
        return "/test_travelplan";
    }
    @PostMapping("/plans/new")
    public String createPlan(HttpSession session,TravelPlansDTO tpDTO){
        Users user = (Users)session.getAttribute("user");
        if (user != null) {
            TravelPlans travelPlans = TravelPlans.builder()
                    .nation(tpDTO.getNation())
                    .title(tpDTO.getTitle())
                    .detail(tpDTO.getDetail())
                    .fileName(tpDTO.getFileName())
                    .filePath(tpDTO.getFilePath())
                    .user(user)
                    .build();
//        user 생성 필요
            travelPlansService.postPlan(travelPlans);
        }
        return "redirect:/";
    }

}
