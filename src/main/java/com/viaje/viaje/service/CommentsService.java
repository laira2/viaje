package com.viaje.viaje.service;

import com.viaje.viaje.dto.CommentsDTO;
import com.viaje.viaje.model.Comments;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Comments;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.CommentsRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import com.viaje.viaje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CommentsService {

    private static final Logger logger = LoggerFactory.getLogger(CommentsService.class);

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private TravelPlansRepository travelPlansRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CommentsDTO addComment(CommentsDTO commentDTO) {
        logger.debug("Plan ID: {}", commentDTO.getPlanId());
        logger.debug("User ID: {}", commentDTO.getUserId());

        TravelPlans plan = travelPlansRepository.findById(commentDTO.getPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid plan ID"));
        Users user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Long parentId = commentDTO.getParentId(); // parentId는 Long 타입으로 선언

        Comments parentComment = null;

        Comments comment = Comments.builder()
                .plan(plan)
                .user(user)
                .content(commentDTO.getContent())
                .parentComment(parentComment)
                .createdAt(LocalDateTime.now()) // 현재 시간을 생성 시간으로 설정
                .updatedAt(LocalDateTime.now()) // 현재 시간을 업데이트 시간으로 설정
                .build();

        Comments savedComment = commentsRepository.save(comment);
        CommentsDTO savedCommentDTO = savedComment.toDTO();
        savedCommentDTO.setCreatedAtString(savedComment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return savedCommentDTO;
    }

    @Transactional
    public CommentsDTO updateComment(Long commentId, String content) {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        comment.update(content);
        comment.setUpdatedAt(LocalDateTime.now()); // 업데이트 시간을 현재 시간으로 설정
        commentsRepository.save(comment);
        return comment.toDTO();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    public List<Comments> getCommentsByPlanId(Long planId) {
        List<Comments> comments = commentsRepository.findByPlan_PlanId(planId);

        // 디버그 로그 추가
        if (comments != null && !comments.isEmpty()) {
            comments.forEach(comment -> logger.debug("레포지토리 코멘트 불러오기: {}", comment));
        } else {
            logger.debug("No comments found for planId: {}", planId);
        }

        return comments;
    }

    @Transactional(readOnly = true)
    public List<CommentsDTO> getRepliesByCommentId(Long parentId) {
        List<Comments> replies = commentsRepository.findByParentComment_CommentId(parentId);
        return replies.stream()
                .map(Comments::toDTO) // 변경된 부분
                .collect(Collectors.toList());
    }
}