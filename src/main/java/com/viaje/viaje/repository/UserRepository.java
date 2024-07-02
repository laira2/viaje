package com.viaje.viaje.repository;

import com.viaje.viaje.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Users> findByUserId(long userId);
    boolean existsByUserId(long userId);  // 사용자 ID 존재 여부 확인
}
