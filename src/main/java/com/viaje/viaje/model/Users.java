    package com.viaje.viaje.model;

    import lombok.*;
    import jakarta.persistence.*;
    import java.time.LocalDateTime;
    import java.util.UUID;

    @Entity  // JPA Entity임을 나타내는 어노테이션
    @NoArgsConstructor  // 기본 생성자를 자동으로 생성해주는 Lombok 어노테이션
    @AllArgsConstructor  // 모든 필드를 포함하는 생성자를 자동으로 생성해주는 Lombok 어노테이션
    @Builder  // 빌더 패턴을 사용할 수 있게 해주는 Lombok 어노테이션
    @Getter  // 모든 필드의 Getter 메서드를 자동으로 생성해주는 Lombok 어노테이션
    @Setter  // 모든 필드의 Setter 메서드를 자동으로 생성해주는 Lombok 어노테이션
    public class Users {

        @Id  // 엔티티의 기본 키임을 나타내는 어노테이션
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 생성 전략을 설정하는 어노테이션
        private Long userId;  // 사용자 ID

        @Column(name = "uuid", updatable = false, nullable = false)  // 데이터베이스 컬럼 설정을 지정합니다.
        private String uuid;  // UUID는 문자열로 저장될 것입니다.

        @Column(nullable = false)
        private String userName;  // 사용자 이름

        @Column(nullable = false)
        private String nickname;  // 닉네임

        @Column(nullable = false)
        private String password;  // 비밀번호

        @Column(nullable = false)
        private String email;  // 이메일 주소

        @Column(nullable = false)
        private LocalDateTime createdAt;  // 생성 일시

        @Column(nullable = false)
        private LocalDateTime updatedAt;  // 업데이트 일시

        @Column(nullable = false)
        private String point;  // 포인트

        @PrePersist  // 엔티티가 저장되기 전에 실행될 메서드를 지정하는 JPA 어노테이션
        protected void onCreate() {
            this.uuid = UUID.randomUUID().toString();  // 랜덤 UUID를 생성하여 문자열로 변환하여 UUID 필드에 설정
            this.createdAt = LocalDateTime.now();  // 현재 시간을 생성 일시로 설정
            this.updatedAt = LocalDateTime.now();  // 현재 시간을 업데이트 일시로 설정
            // 포인트 초기값 설정
            if (this.point == null) {
                this.point = "0";  // 기본 포인트를 0으로 설정
            }
        }

        @PreUpdate  // 엔티티가 업데이트되기 전에 실행될 메서드를 지정하는 JPA 어노테이션
        protected void onUpdate() {
            this.updatedAt = LocalDateTime.now();  // 업데이트가 발생할 때마다 현재 시간을 업데이트 일시로 설정
        }
    }