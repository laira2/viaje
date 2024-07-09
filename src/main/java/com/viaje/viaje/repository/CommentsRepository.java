package com.viaje.viaje.repository;

import com.viaje.viaje.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPlan_PlanId(Long planId); // 특정 상품(plan)에 대한 댓글 조회
    List<Comments> findByParentComment_CommentId(Long parentId); // 특정 부모 댓글(parentComment)의 자식 댓글 조회
    List<Comments> findByUser_UserId(Long userId); // 특정 사용자(user)가 작성한 댓글 조회
}