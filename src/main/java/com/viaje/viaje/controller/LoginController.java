package com.viaje.viaje.controller;

import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/loginPage")
    public  String loginUser(UserDTO userDTO, HttpSession session, Model model) {
        boolean isAuthenticated = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());

        logger.info("로그인 isauthenticated :", userService.authenticate(userDTO.getEmail(), userDTO.getPassword()));
        if (isAuthenticated) {
            session.setAttribute("user", userDTO.getEmail());
            session.setAttribute("userName", userDTO.getUserName());
            model.addAttribute("isLoggedIn", true);
            return "main";
        } else {
            return "/login";

        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}

//수정