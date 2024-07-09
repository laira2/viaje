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

@Controller
@RequestMapping("/comments") // 모든 메소드에 대해 기본 경로 설정
public class CommentsController {

    private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private TravelPlansRepository travelPlansRepository;

    @PostMapping("/add")
    public ResponseEntity<CommentsDTO> addCommentFromUrlParam(
            @RequestParam("comments") String comment,
            @RequestParam("planId") Long planId,
            @RequestParam("userId") Long userId) {

        CommentsDTO commentDTO = new CommentsDTO();
        commentDTO.setContent(comment);
        commentDTO.setPlanId(planId);
        commentDTO.setUserId(userId);

        CommentsDTO createdComment = commentsService.addComment(commentDTO);
        return ResponseEntity.ok(createdComment); // JSON 응답으로 반환
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<CommentsDTO>> getCommentsByPlanId(@PathVariable Long planId) {
        List<CommentsDTO> comments = commentsService.getCommentsByPlanId(planId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentsDTO> updateComment(
            @PathVariable Long commentId,
            @RequestParam("content") String content) {
        CommentsDTO updatedComment = commentsService.updateComment(commentId, content);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        try {
            commentsService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/replies/{commentId}")
    public ResponseEntity<List<CommentsDTO>> getRepliesByCommentId(@PathVariable Long commentId) {
        List<CommentsDTO> replies = commentsService.getRepliesByCommentId(commentId);
        return ResponseEntity.ok(replies);
    }

    @GetMapping("/plan/details/{planId}")
    public String getPlanDetails(@PathVariable Long planId, Model model, HttpServletRequest request) {

        // 주어진 planId에 해당하는 댓글 목록 호출
        List<CommentsDTO> comments = commentsService.getCommentsByPlanId(planId);
        model.addAttribute("comments", comments);

        // 세션에서 사용자 정보 호출
        Users user = (Users) request.getSession().getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            // 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        // 주어진 planId에 해당하는 여행 계획 정보 호출
        Optional<TravelPlans> optionalPlan = travelPlansRepository.findById(planId);
        if (optionalPlan.isPresent()) {
            TravelPlans plan = optionalPlan.get();
            model.addAttribute("selectedPlan", plan);
        } else {
            // 여행 계획이 존재하지 않을 경우 에러 페이지로 리다이렉트
            return "error_page";
        }
        // 데이터를 담은 모델을 뷰로 전달
        return "test_product_detail";
    }
}