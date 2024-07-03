package com.viaje.viaje.service;

import com.viaje.viaje.dto.BoardDTO;
import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.BoardRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final TravelPlansRepository travelPlansRepository;
    private final UserService userService;

    @Autowired
    public BoardService(BoardRepository boardRepository, TravelPlansRepository travelPlansRepository, UserService userService) {
        this.boardRepository = boardRepository;
        this.travelPlansRepository = travelPlansRepository;
        this.userService = userService;
    }
    public List<Board> findAllBoardProduct (){
        List<Board> board_list = boardRepository.findAll();
        return board_list;
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
