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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String postPlan(@RequestParam(value = "tagsOptions", required = false)String[] tagsOptions, HttpSession session, TravelPlansDTO tpDTO){

        session.setAttribute("tagsOptions",tagsOptions);
        TravelPlans created_plan = travelPlansService.createPlan(session,tpDTO);
        return "/product/detial";
    }

}
