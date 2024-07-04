package com.viaje.viaje.service;

import com.viaje.viaje.dto.CommentsDTO;
import com.viaje.viaje.model.Comments;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.CommentsRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import com.viaje.viaje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        if (parentId != null) { // null 체크를 추가하여 NullPointerException 방지
            parentComment = commentsRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent comment ID"));
        }

        Comments comment = Comments.builder()
                .plan(plan)
                .user(user)
                .content(commentDTO.getContent())
                .parentComment(parentComment)
                .createdAt(LocalDateTime.now()) // 현재 시간을 생성 시간으로 설정
                .updatedAt(LocalDateTime.now()) // 현재 시간을 업데이트 시간으로 설정
                .build();

        Comments savedComment = commentsRepository.save(comment);
        commentDTO.setCommentId(savedComment.getCommentId());

        // 로그확인
        logger.debug("Added new comment with ID: {}", savedComment.getCommentId());
        return commentDTO;
    }

    @Transactional
    public CommentsDTO updateComment(Long commentId, String content) {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        comment.update(content);
        comment.setUpdatedAt(LocalDateTime.now()); // 업데이트 시간을 현재 시간으로 설정
        commentsRepository.save(comment);
        return new CommentsDTO(
                comment.getCommentId(),
                comment.getPlan().getPlanId(),
                comment.getUser().getUserId(),
                comment.getContent(),
                comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null,
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    public List<CommentsDTO> getCommentsByPlanId(Long planId) {
        List<Comments> comments = commentsRepository.findAllByPlan_PlanId(planId);
        return comments.stream()
                .map(comment -> new CommentsDTO(
                        comment.getCommentId(),
                        comment.getPlan().getPlanId(),
                        comment.getUser().getUserId(),
                        comment.getContent(),
                        comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null,
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentsDTO> getRepliesByCommentId(Long parentId) {
        List<Comments> replies = commentsRepository.findAllByParentComment_CommentId(parentId);
        return replies.stream()
                .map(comment -> new CommentsDTO(
                        comment.getCommentId(),
                        comment.getPlan().getPlanId(),
                        comment.getUser().getUserId(),
                        comment.getContent(),
                        comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null,
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
}
