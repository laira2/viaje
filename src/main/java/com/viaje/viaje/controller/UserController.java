package com.viaje.viaje.controller;

import com.viaje.viaje.model.User;
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
    public User registerUser(@RequestBody UserDTO userDTO) {
        // 여기에서 필요한 유효성 검사나 추가적인 로직을 수행할 수 있습니다.
        return userService.registerUser(userDTO);
    }

    // 아이디 중복 검사 API
    @GetMapping("/check-username")
    public boolean checkUserName(@RequestParam String user_id) {
        return userService.checkUserId(Long.parseLong(user_id)); // 사용자 아이디 중복 검사
    }

    // 이메일 중복 검사 API
    @GetMapping("/check-email")
    public boolean checkEmail(@RequestParam String email) {
        return userService.checkEmail(email);
    }

}