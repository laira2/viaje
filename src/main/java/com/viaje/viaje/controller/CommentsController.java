package com.viaje.viaje.controller;

import com.viaje.viaje.dto.CommentsDTO;
import com.viaje.viaje.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final Logger logger = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/add")
    public ResponseEntity<CommentsDTO> addCommentFromUrlParam(
            @RequestParam("comment") String comment,
            @RequestParam("planId") Long planId,
            @RequestParam("userId") Long userId) {
        CommentsDTO commentDTO = new CommentsDTO();
        commentDTO.setContent(comment);
        commentDTO.setPlanId(planId); // Plan ID 설정
        commentDTO.setUserId(userId); // User ID 설정

        logger.debug("Received comment from URL param: {}", comment);
        logger.debug("Plan ID: {}", commentDTO.getPlanId());
        logger.debug("User ID: {}", commentDTO.getUserId());
        commentDTO.setContent(comment);

        // 로그 추가
        logger.debug("Received comment: {}", comment);

        CommentsDTO createdComment = commentsService.addComment(commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    @PostMapping("/add-json")
    public ResponseEntity<CommentsDTO> addCommentFromJson(@RequestBody CommentsDTO commentDTO) {

        // 로그 추가
        logger.debug("Received comment from JSON: {}", commentDTO.getContent());
        logger.debug("Plan ID: {}", commentDTO.getPlanId());
        logger.debug("User ID: {}", commentDTO.getUserId());

        CommentsDTO createdComment = commentsService.addComment(commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentsDTO> updateComment(@PathVariable Long commentId, @RequestBody String content) {
        CommentsDTO updatedComment = commentsService.updateComment(commentId, content);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentsService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<CommentsDTO>> getCommentsByPlanId(@PathVariable Long planId) {
        List<CommentsDTO> comments = commentsService.getCommentsByPlanId(planId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/replies/{commentId}")
    public ResponseEntity<List<CommentsDTO>> getRepliesByCommentId(@PathVariable Long commentId) {
        List<CommentsDTO> replies = commentsService.getRepliesByCommentId(commentId);
        return ResponseEntity.ok(replies);
    }
}
