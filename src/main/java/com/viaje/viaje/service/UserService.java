package com.viaje.viaje.service;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.dto.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

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
        userRepository.save(user);
        return user;
    }

    //이메일 유효성 검사
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // 닉네임 유효성 검사
    public boolean checkNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    //로그인 기능 구현
    public boolean authenticate(String email, String password) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return user.getPassword().equals(password); // 실제 환경에서는 비밀번호를 해시하고 비교해야 합니다.
        }
        return false;
    }

    // 마이페이지 조회를 위해 추가
    public Users findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
    }

    public Users findByEmail(String userEmail) {
        Optional<Users> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            // 사용자가 존재하지 않는 경우 처리
            throw new UserNotFoundException("User not found with email: " + userEmail);
        }
    }
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    @Transactional
    public void updateUserPoints(Long userId, Integer additionalPoints) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        user.setPoint(user.getPoint() + additionalPoints);
        userRepository.save(user);
    }
}