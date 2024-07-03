package com.viaje.viaje.repository;

import com.viaje.viaje.model.PlanTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTagRepository extends JpaRepository<PlanTag, PlanTag.PlanTagId> {
    // 추가적인 쿼리 메소드를 여기에 정의할 수 있습니다.
}