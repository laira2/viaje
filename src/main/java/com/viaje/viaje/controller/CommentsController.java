package com.viaje.viaje.controller;

import com.viaje.viaje.dto.CommentsDTO;
import com.viaje.viaje.model.Comments;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.CommentsRepository;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import com.viaje.viaje.service.CommentsService;
import com.viaje.viaje.service.TravelPlansService;
import com.viaje.viaje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.viaje.viaje.service.TravelPlansService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller// 모든 메소드에 대해 기본 경로 설정
public class CommentsController {

    private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private TravelPlansRepository travelPlansRepository;


    @PostMapping("/comment/add")    //댓글 추가
    public String addCommentFromUrlParam(
            @RequestParam("comments") String comment,
            @RequestParam("planId") Long planId,
            @RequestParam("userId") Long userId) {

        CommentsDTO commentDTO = new CommentsDTO();
        commentDTO.setContent(comment);
        commentDTO.setPlanId(planId);
        commentDTO.setUserId(userId);

        CommentsDTO createdComment = commentsService.addComment(commentDTO);
        return "redirect:/product_detail/" + planId;
    }

    @PostMapping("/modify/{commentId}")  //수정
    public String updateComment(
            @PathVariable Long commentId,
            @RequestParam("content") String content, HttpSession session) {
        CommentsDTO updatedComment = commentsService.updateComment(commentId, content);
        TravelPlans plan = (TravelPlans)session.getAttribute("selectedPlan");

        return "redirect:/product_detail/" + plan.getPlanId();
    }

    @DeleteMapping("/delete/{commentId}") //삭제
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, HttpSession session) {
        try {
            //if문 세션 user, comment user 동일하면 삭제
            commentsService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // planId값을 가진 모든 comments 불러오기
    public List<Comments> getComments(@PathVariable Long planId) {

        // 주어진 planId에 해당하는 댓글 목록 호출
        List<Comments> comments = commentsService.getCommentsByPlanId(planId);
        logger.info("comments list : {}", commentsService.getCommentsByPlanId(planId));

        return comments;
    }
}
