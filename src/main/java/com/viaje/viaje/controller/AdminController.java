package com.viaje.viaje.controller;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String listPlans(HttpSession session, Model model) {

        String email = (String) session.getAttribute("user");
        if (email != null && adminService.isAdmin(email)) {
            return "/admin";
        } else {
            return "redirect:/";
        }
    }
}
