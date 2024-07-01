package com.viaje.viaje.controller;

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
        return "/plans/createPlanForm";
    }

}
