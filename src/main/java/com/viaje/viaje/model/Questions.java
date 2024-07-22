package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "answers"})
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionsId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name="userId")
    private Users user;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answers> answers = new ArrayList<>();

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaStatus qnaStatus = QnaStatus.확인중;

    public void addAnswer(Answers answer) {
        answers.add(answer);
        answer.setQuestions(this);
    }

    // 답변을 제거하는 편의 메서드
    public void removeAnswer(Answers answer) {
        answers.remove(answer);
        answer.setQuestions(null);
    }

    public enum QnaStatus {
        확인중, 답변완료
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (qnaStatus == null) {
            qnaStatus = QnaStatus.확인중;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}