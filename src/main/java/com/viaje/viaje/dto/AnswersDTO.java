package com.viaje.viaje.dto;

import com.viaje.viaje.model.Questions;
import com.viaje.viaje.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswersDTO {
    private Long answersId;
    private Users user;
    private String contents;
    private Questions questions;
}
