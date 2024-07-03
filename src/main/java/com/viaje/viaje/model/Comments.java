package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @JoinColumn(name ="planId" , nullable = false)
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

    private long parentId; // 부모 댓글의 코멘트Id 참조

    // 댓글 수정
    public void update(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    // 댓글 삭제
    public void delete() {
        // 필요한 삭제 로직 추가 (예: Soft Delete 혹은 실제 데이터베이스에서 삭제)
    }

    @PrePersist  // 엔티티가 저장되기 전에 실행될 메서드를 지정하는 JPA 어노테이션
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // 현재 시간을 생성 일시로 설정
        this.updatedAt = LocalDateTime.now();

    }

}
