package com.viaje.viaje.controller;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 닉네임 중복 검사 API
    @GetMapping("/join/check-nickname")
    @ResponseBody
    public boolean checkNicname(@RequestParam String nickname) {
        return userService.checkNickname(nickname);
    }

    // 가입 성공 후 리턴 페이지
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 파일 반환
    }

    // 로그인 기능 구현
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        boolean isAuthenticated = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }

}