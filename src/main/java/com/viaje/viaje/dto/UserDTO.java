package com.viaje.viaje.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long user_id;  // 사용자 ID
    private String uuid;  // UUID는 문자열로 저장됩니다.
    private String user_name;  // 사용자 이름
    private String nickname;  // 닉네임
    private String password;  // 비밀번호
    private String email;  // 이메일 주소
    private LocalDateTime created_at;  // 생성 일시
    private LocalDateTime updated_at;  // 업데이트 일시
    private String point;  // 포인트

}
