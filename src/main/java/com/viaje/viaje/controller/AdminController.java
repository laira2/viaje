package com.viaje.viaje.controller;

import com.viaje.viaje.model.Board;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String listPlans(HttpSession session, Model model) {
        return "/admin";
    }
}
