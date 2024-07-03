package com.viaje.viaje.controller;

import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    //세션 정보 확인용
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    // 로그인 기능 구현
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        boolean isAuthenticated = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());
        if (isAuthenticated) {
            session.setAttribute("user", userDTO.getEmail());
            logger.info("User logged in: {}", userDTO.getEmail());

            // 세션 정보 로그
            logger.info("Session ID: {}", session.getId());
            logger.info("Session User: {}", session.getAttribute("user"));

            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    // 로그아웃 기능 구현
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
