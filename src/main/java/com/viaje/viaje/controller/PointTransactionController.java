package com.viaje.viaje.controller;

import com.viaje.viaje.model.PointTransaction;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.PointTransactionRepository;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import com.viaje.viaje.dto.PointTransactionDTO;
import com.viaje.viaje.service.PointTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class PointTransactionController {
    private final PointTransactionRepository pointTransactionRepository;
    private final PointTransactionService pointTransactionService;
    private final UserService userService;

    public PointTransactionController(PointTransactionRepository pointTransactionRepository, PointTransactionService pointTransactionService, UserService userService) {
        this.pointTransactionRepository = pointTransactionRepository;
        this.pointTransactionService = pointTransactionService;
        this.userService = userService;
    }

    @PostMapping("/charge")
    public String chargePoints(@RequestParam Long userId,
                                                            @RequestParam Integer chargeAmount,
                                                            @RequestParam Integer chargePoint) {
        PointTransactionDTO transactionDTO = pointTransactionService.chargePoints(userId, chargeAmount, chargePoint);
        return "redirect:/mypage";
    }

    @GetMapping("/pointcharge")
    public String pointCharge(HttpSession session, Model model){
        return "/charge";
    }

    @PostMapping("/requestcharge")
    public String requestCharge(HttpSession session, @RequestParam("chargeAmount") String chargeAmount, Model model){
        String tossOrderId = generateUniqueOrderId();
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        int chargeamount = Integer.parseInt(chargeAmount);
        UUID userUUID = UUID.fromString(userService.findByEmail((String) session.getAttribute("user")).getUuid());
        session.setAttribute("user_model",user);
        model.addAttribute("userUUID", userUUID);
        model.addAttribute("chargeAmount", chargeamount);
        model.addAttribute("orderId", tossOrderId);
        return "/toss_index";
    }

    private String generateUniqueOrderId() {
        // 현재 시간을 밀리초로 얻기
        long timestamp = System.currentTimeMillis();
        // UUID 생성
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 타임스탬프와 UUID를 조합하여 64자 이내로 생성
        String orderId = String.format("ord_%d_%s", timestamp, uuid);
        // 64자로 제한
        return orderId.substring(0, Math.min(orderId.length(), 64));
    }

    @GetMapping("/adminCharging")
    public String adminCharge(Model model) {
        List<PointTransaction> pointList = pointTransactionRepository.findAll();
        model.addAttribute("pointList", pointList);
        return "charging";
    }
}
