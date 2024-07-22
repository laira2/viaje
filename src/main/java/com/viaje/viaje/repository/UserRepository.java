package com.viaje.viaje.repository;

import com.viaje.viaje.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Users> findByNickname(String nickname);
    boolean existsByNickname(String nickname);

    @Query("SELECT SUM(u.point) FROM Users u")
    Long sumAllPoints();
}