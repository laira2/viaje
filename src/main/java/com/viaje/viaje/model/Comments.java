package com.viaje.viaje.model;

import com.viaje.viaje.dto.CommentsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planId", referencedColumnName = "planId", nullable = false) //referencedColumnName 참조 외래키
    private TravelPlans plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="userId" , nullable = false)
    private Users user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId", nullable = true)
    private Comments parentComment; // 부모 댓글의 코멘트Id 참조

    // 댓글 수정
    public void update(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist  // 엔티티가 저장되기 전에 실행될 메서드를 지정하는 JPA 어노테이션
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // 현재 시간을 생성 일시로 설정
        this.updatedAt = LocalDateTime.now();

    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Comments 엔티티에서 CommentsDTO로의 변환 메서드 추가 (옵셔널)
    public CommentsDTO toDTO() {
        return new CommentsDTO(
                this.commentId,
                this.plan.getPlanId(),
                this.user.getUserId(),
                this.content,
                this.parentComment != null ? this.parentComment.getCommentId() : null,
                this.createdAt,
                this.updatedAt,
                this.user.getNickname(), // Users 객체에서 nickname 가져오기
                this.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // 문자열로 변환
    }
}
