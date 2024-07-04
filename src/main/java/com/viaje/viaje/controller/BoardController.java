package com.viaje.viaje.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.service.BoardService;
import com.viaje.viaje.service.TravelPlansService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    public final BoardService boardService;

    public final TravelPlansService travelPlansService;

    public BoardController(BoardService boardService, TravelPlansService travelPlansService) {
        this.boardService = boardService;
        this.travelPlansService = travelPlansService;
    }

    @GetMapping("/product/all")
    public String listPlans(HttpSession session, Model model) {
        List<Board> boardList = boardService.findAllBoardProduct();
        model.addAttribute("boardList", boardList);
        return "/test_product";
    }

    @GetMapping("/product/{type}")
    public String themePlans(@PathVariable("type") String type, HttpSession session,Model model ){
        List<Board> listTypePlans = boardService.findProductByType(type);
        model.addAttribute(listTypePlans);
        return "/test_product";
    }

    @GetMapping("/product_detail/{id}")
    public String productDetial(@PathVariable("id")Long id, HttpSession session, Model model){
        TravelPlans selectedPlan = travelPlansService.findByPlanId(id);
        model.addAttribute("selectedPlan",selectedPlan);
        return "/test_product_detail";
    }


}
