package com.viaje.viaje.service;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.BoardRepository;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private TravelPlansRepository travelPlansRepository;

    @Autowired
    private TravelPlansService travelPlansService;

    @Autowired
    private BoardRepository boardRepository;

    public boolean isAdmin(String email) {
        Users user = userRepository.findByEmail(email).orElse(null);
        return user != null && user.isAdmin();
    }

    public List<Board> findAllBoardProduct() {
        return boardService.findAllBoardProduct();
    }

    public List<Board> findPendingTravelPlans() {
        return boardRepository.findAllByBoardStatus(Board.BoardStatus.PENDING);
    }

    public void approvePlan(Long id) {
        travelPlansService.approvePlan(id);
        boardService.approvePlan(id);
    }

    public void updatePlanStatus(Long id, String status) {
        Board.BoardStatus boardStatus = Board.BoardStatus.valueOf(status);
        TravelPlans.PlanStatus planStatus = TravelPlans.PlanStatus.valueOf(status);
        TravelPlans updatePlan = travelPlansRepository.findById(id).orElseThrow();
        Board updateBoard = boardRepository.findByTravelPlans(updatePlan);

        boardRepository.updateBoardStatus(updateBoard.getBoardId(), boardStatus);
        travelPlansRepository.updatePlanStatus(id, planStatus);
    }
}
