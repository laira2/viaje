package com.viaje.viaje.dto;

import com.viaje.viaje.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {

    private Long commentId;
    private Long planId;
    private Long userId;
    private String content;
    private Long parentId; // 부모 댓글의 코멘트Id 참조
    private LocalDateTime createdAt;  // 생성 일시
    private LocalDateTime updatedAt;
    private String nickname; // 추가된 필드
    private String createdAtString;
}
