package com.viaje.viaje.service;

import com.viaje.viaje.dto.BoardDTO;
import com.viaje.viaje.model.*;
import com.viaje.viaje.repository.BoardRepository;
import com.viaje.viaje.repository.PlanTagRepository;
import com.viaje.viaje.repository.TagsRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final TravelPlansRepository travelPlansRepository;
    private final UserService userService;
    private final PlanTagRepository planTagRepository;
    private final TagsRepository tagsRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, TravelPlansRepository travelPlansRepository, UserService userService, PlanTagRepository planTagRepository, TagsRepository tagsRepository) {
        this.boardRepository = boardRepository;
        this.travelPlansRepository = travelPlansRepository;
        this.userService = userService;
        this.planTagRepository = planTagRepository;
        this.tagsRepository = tagsRepository;
    }
    public List<Board> findAllBoardProduct (){
        return boardRepository.findAll();
    }
    public List<Board> findProductByType(String type) {
        Tags tagType = tagsRepository.findByTagName(type);
        if (tagType == null) {
            return Collections.emptyList(); // 태그를 찾지 못한 경우 빈 리스트 반환
        }

        List<PlanTag> typePlanTags = planTagRepository.findAllByTags(tagType);
        List<Board> typeProducts = new ArrayList<>();

        for (PlanTag planTag : typePlanTags) {
            Board board = boardRepository.findByTravelPlans(planTag.getTravelPlans());
            if (board != null) {
                typeProducts.add(board);
            }
        }

        return typeProducts;
    }

    public void postPlan(Users user, TravelPlans travelPlans) {
        Board createPlanBoard = Board.builder()
                .travelPlans(travelPlans)
                .user(user)
                .title(travelPlans.getTitle())
                .build();
        boardRepository.save(createPlanBoard);
    }


}
