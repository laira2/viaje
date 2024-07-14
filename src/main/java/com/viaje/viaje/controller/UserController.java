package com.viaje.viaje.controller;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

    private final UserService userService;

    // 세션 정보 확인용
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // join.html 뷰를 반환하는 메소드
    @GetMapping("/join")
    public String join() {
        return "join"; // join.html 파일 반환
    }

    // 회원 가입 API
    @PostMapping("/join/register")
    public String registerUser(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
        try {
            // 회원가입 처리
            Users createdUser = userService.registerUser(userDTO);

            // 회원가입 후 자동 로그인 처리
            session.setAttribute("user", userDTO.getEmail());
            logger.info("User registered and logged in: {}", userDTO.getEmail());
            logger.info("Session ID: {}", session.getId());
            session.setAttribute("isLoggedIn", true);
            // 가입 처리 후, 리다이렉트할 경로를 반환
            return "redirect:/"; // 혹은 다른 리다이렉트할 경로 설정
        } catch (Exception e) {
            logger.error("회원가입 및 로그인 실패: {}", e.getMessage());
            return "redirect:/join"; // 실패 시 리다이렉트할 경로 설정
        }
    }

    // 이메일 중복 검사 API
    @GetMapping("/join/check-email")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        return userService.checkEmail(email);
    }

    // 닉네임 중복 검사 API
    @GetMapping("/join/check-nickname")
    @ResponseBody
    public boolean checkNickname(@RequestParam String nickname) {
        return userService.checkNickname(nickname);
    }

    // 가입 성공 후 리턴 페이지
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 파일 반환
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

}


