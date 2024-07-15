package com.viaje.viaje.controller;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.PointTransaction;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.service.BoardService;
import com.viaje.viaje.service.PointTransactionService;
import com.viaje.viaje.service.UserService;
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

    @Autowired
    public MypageController(UserService userService, PointTransactionService pointTransactionService) {
        this.userService = userService;
        this.pointTransactionService = pointTransactionService;
    }

    @GetMapping("/mypage/{userId}")
    public String getMypage(@PathVariable Long userId, Model model) {
        // 유저 정보 가져오기
        Users user = userService.findById(userId);
        model.addAttribute("user", user);

        // 포인트 충전 내역 가져오기
        List<PointTransaction> transactions = pointTransactionService.getPointTransactionsByUserId(userId);
        model.addAttribute("transactions", transactions);

        return "mypage";
    }
}
