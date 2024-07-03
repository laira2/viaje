package com.viaje.viaje.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.service.BoardService;
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

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/product/All")
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

}
