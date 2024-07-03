package com.viaje.viaje.controller;

import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.service.TravelPlansService;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelPlansController {

    public final TravelPlansService travelPlansService;
    public final UserService userService;

    @Autowired
    public TravelPlansController(TravelPlansService travelPlansService, UserService userService){

        this.travelPlansService = travelPlansService;
        this.userService = userService;
    }
    @GetMapping("/plans/new")
    public String createPlanForm(HttpSession session){

        return "/test_travelplan";
    }
    @PostMapping("/plans/new")
    public String postPlan(HttpSession session,TravelPlansDTO tpDTO){
        String user_email = (String) session.getAttribute("user");
        Users user = userService.findByEmail(user_email);
        TravelPlans created_plan = travelPlansService.createPlan(user,tpDTO);

        return "redirect:/board/detial";
    }

}
