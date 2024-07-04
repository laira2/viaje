package com.viaje.viaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsDTO {

    private long commentId;
    private long planId;
    private long userId;
    private String content;
    private long parentId; // 부모 댓글의 코멘트Id 참조
}
