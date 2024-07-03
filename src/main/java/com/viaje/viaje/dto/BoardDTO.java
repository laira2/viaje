package com.viaje.viaje.dto;

import com.viaje.viaje.model.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long boardId;
    private Long planId;
    private Long userId;
    private Integer viewCount;
    private Integer likeCount;
    private Board.BoardStatus boardStatus;
    private String title;


}
