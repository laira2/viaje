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
        // 이메일 중복 검사
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + userDTO.getEmail());
        }

        // 사용자 아이디 중복 검사
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다: " + userDTO.getUserId());
        }

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

    public Users findUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다: " + userId));
    }

    public boolean checkUserId(long userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
