package com.viaje.viaje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }

    @GetMapping("/yac")
    public String showYacPage() {
        return "yac"; // yac.html로 이동
    }


}
