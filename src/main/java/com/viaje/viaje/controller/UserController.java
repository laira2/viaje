package com.viaje.viaje.controller;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

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
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        Users createdUser = userService.registerUser(userDTO);
        // 가입 처리 후, 리다이렉트할 경로를 반환
        return "redirect:/login";
    }


    // 이메일 중복 검사 API
    @GetMapping("/join/check-email")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        return userService.checkEmail(email);
    }



    // 가입 성공 후 리턴 페이지
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 파일 반환
    }
}