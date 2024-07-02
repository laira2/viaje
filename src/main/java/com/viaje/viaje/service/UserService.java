package com.viaje.viaje.service;

import com.viaje.viaje.model.User;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;  // UserRepository 의존성 주입
        this.passwordEncoder = passwordEncoder;  // BCryptPasswordEncoder 의존성 주입
    }

    public User registerUser(UserDTO userDTO) {
        // 이메일 중복 검사
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + userDTO.getEmail());
        }

        // 사용자 아이디 중복 검사
        if (userRepository.findByUserId(userDTO.getUser_id()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다: " + userDTO.getUser_id());
        }

        // User 객체 생성 및 저장
        User user = User.builder()
                .user_id(userDTO.getUser_id()) // 사용자 아이디 설정
                .user_name(userDTO.getUser_name())  // 사용자 이름 설정
                .nickname(userDTO.getNickname())  // 닉네임 설정
                .password(passwordEncoder.encode(userDTO.getPassword()))  // 비밀번호 설정 (암호화)
                .email(userDTO.getEmail())  // 이메일 주소 설정
                .created_at(userDTO.getCreated_at() != null ? userDTO.getCreated_at() : LocalDateTime.now())  // 생성 일시 설정
                .updated_at(userDTO.getUpdated_at() != null ? userDTO.getUpdated_at() : LocalDateTime.now())  // 업데이트 일시 설정
                .point(userDTO.getPoint() != null ? userDTO.getPoint() : "0")  // 포인트 설정 (기본값 '0')
                .build();  // User 객체 빌드

        return userRepository.save(user);  // User 저장 후 반환
    }

    public User findUserById(long userId) {
        return userRepository.findById(userId)  // 사용자 ID로 검색
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다: " + userId));  // 찾지 못하면 예외 처리
    }

    public boolean checkUserId(long user_id) {
        return userRepository.findByUserId(user_id).isPresent(); // 사용자 아이디 중복 검사
    }

    public boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
