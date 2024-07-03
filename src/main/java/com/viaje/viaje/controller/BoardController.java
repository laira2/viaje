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

import java.util.List;

@Controller
public class BoardController {
    public final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/product")
    public String listPlans(HttpSession session, Model model) throws JsonProcessingException {
        List<Board> board_list = boardService.findAllBoardProduct();
        model.addAttribute("board_list", board_list);
        return "/test_product";
    }

}
