package com.viaje.viaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsDTO {

    private long commentId;
    private long planId;
    private long userId;
    private String content;
    private long parentId; // 부모 댓글의 코멘트Id 참조
    private LocalDateTime createdAt;  // 생성 일시
    private LocalDateTime updatedAt;
}
