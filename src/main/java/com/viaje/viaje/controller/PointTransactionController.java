package com.viaje.viaje.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import com.viaje.viaje.dto.PointTransactionDTO;
import com.viaje.viaje.service.PointTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
public class PointTransactionController {

    private final PointTransactionService pointTransactionService;

    @Autowired
    public PointTransactionController(PointTransactionService pointTransactionService) {
        this.pointTransactionService = pointTransactionService;
    }

    @PostMapping("/charge")
    public String chargePoints(@RequestParam Long userId,
                                                            @RequestParam Integer chargeAmount,
                                                            @RequestParam Integer chargePoint) {
        PointTransactionDTO transactionDTO = pointTransactionService.chargePoints(userId, chargeAmount, chargePoint);
        return "redirect:/mypage";
    }
}
