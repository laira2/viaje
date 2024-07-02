package com.viaje.viaje.controller;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입 API
    @PostMapping("/register")
    public Users registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    // 아이디 중복 검사 API
    @GetMapping("/check-username")
    public boolean checkUserName(@RequestParam("user_id") String userId) {
        return userService.checkUserId(Long.parseLong(userId));
    }

    // 이메일 중복 검사 API
    @GetMapping("/check-email")
    public boolean checkEmail(@RequestParam String email) {
        return userService.checkEmail(email);
    }
}
