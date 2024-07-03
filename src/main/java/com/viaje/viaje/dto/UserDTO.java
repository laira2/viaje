package com.viaje.viaje.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private long userId;  // 사용자 ID
    private String uuid;  // UUID는 문자열로 저장됩니다.
    private String userName;  // 사용자 이름
    private String nickname;  // 닉네임
    private String password;  // 비밀번호
    private String email;  // 이메일 주소
    private LocalDateTime createdAt;  // 생성 일시
    private LocalDateTime updatedAt;  // 업데이트 일시
    private String point;  // 포인트

}