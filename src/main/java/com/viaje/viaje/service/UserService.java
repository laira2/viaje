package com.viaje.viaje.service;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users registerUser(UserDTO userDTO) {
        // User 객체 생성 및 저장
        Users user = Users.builder()
                .userId(userDTO.getUserId())
                .userName(userDTO.getUserName())
                .nickname(userDTO.getNickname())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .createdAt(userDTO.getCreatedAt() != null ? userDTO.getCreatedAt() : LocalDateTime.now())
                .updatedAt(userDTO.getUpdatedAt() != null ? userDTO.getUpdatedAt() : LocalDateTime.now())
                .point(userDTO.getPoint() != null ? userDTO.getPoint() : "0")
                .build();

        return userRepository.save(user);
    }

    //이메일 유효성 검사
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // 닉네임 유효성 검사
    public boolean checkNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}