package com.viaje.viaje.repository;

import com.viaje.viaje.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByPlan_PlanId(Long planId);
    List<Comments> findAllByParentComment_CommentId(Long parentId);
}
