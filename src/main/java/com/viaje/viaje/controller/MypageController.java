package com.viaje.viaje.controller;

import com.viaje.viaje.model.*;
import com.viaje.viaje.repository.BoardRepository;
import com.viaje.viaje.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MypageController {

    private  UserService userService;
    private  PointTransactionService pointTransactionService;
    private BoardRepository boardRepository;
    private final OrdersService ordersService;
    private final LikeService likeService;

    @Autowired
    public MypageController(UserService userService, PointTransactionService pointTransactionService, BoardRepository boardRepository, OrdersService ordersService, LikeService likeService) {
        this.userService = userService;
        this.pointTransactionService = pointTransactionService;
        this.boardRepository = boardRepository;
        this.ordersService = ordersService;
        this.likeService = likeService;
    }

    @GetMapping("/mypage")
    public void getMypage(HttpSession session, Model model) {
        // 유저 정보 가져오기
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        model.addAttribute("user", user);

        // 포인트 충전 내역 가져오기
        List<PointTransaction> transactions = pointTransactionService.getPointTransactionsByUserId(user.getUserId());

        //작성 plan 내역 가져오기
        List<Board> boardList = boardRepository.findAllByUser(user);

        //구매 내역 가져오기
        List<Board> orderBoard = ordersService.orderItemBoard(user);
//        List<OrderItems> oderItemsList = ordersService.orderList(user);
        //좋아요 목록 가져오기
        List<Board> likeBoard = likeService.findLikeBoard(user);

        model.addAttribute("boardList", boardList);
        model.addAttribute("transactions", transactions);
        model.addAttribute("oderBoardList", orderBoard);
        model.addAttribute("likeBoardList", likeBoard);

    }
}
