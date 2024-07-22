package com.viaje.viaje.controller;

import com.viaje.viaje.dto.AnswersDTO;
import com.viaje.viaje.dto.QuestionsDTO;
import com.viaje.viaje.model.*;
import com.viaje.viaje.repository.OrdersItemRepository;
import com.viaje.viaje.repository.PlanDetailRepository;
import com.viaje.viaje.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BoardController {
    public final BoardService boardService;

    public final TravelPlansService travelPlansService;
    public final UserService userService;
    public final CommentsController commentsController;
    private final PlanDetailRepository planDetailRepository;
    private final QnAService qnAService;
    private final OrdersItemRepository ordersItemRepository;
    private final TagsService tagsService;

    public BoardController(BoardService boardService,
                           TravelPlansService travelPlansService,
                           UserService userService,
                           CommentsController commentsController,
                           PlanDetailRepository planDetailRepository,
                           QnAService qnAService,
                           OrdersItemRepository ordersItemRepository, TagsService tagsService) {
        this.boardService = boardService;
        this.travelPlansService = travelPlansService;
        this.userService = userService;
        this.commentsController = commentsController;
        this.planDetailRepository = planDetailRepository;
        this.qnAService = qnAService;
        this.ordersItemRepository = ordersItemRepository;
        this.tagsService = tagsService;
    }

    @GetMapping("/products")
    public String listPlans(HttpSession session, Model model) {
        if (session.getAttribute("isAdmin").equals(true)){
            List<Board> adminBoardList = boardService.findAllBoardProduct();
            model.addAttribute("boardList", adminBoardList);

        }else {
            List<Board> boardList = boardService.findApproved();
            model.addAttribute("boardList", boardList);
        }
        return "/board";
    }

    @GetMapping("/products/{type}")
    public String themePlans(@PathVariable("type") String type, HttpSession session,Model model ){
        List<Board> boardList = boardService.findProductByType(type);
        model.addAttribute("boardList",boardList);
        return "/board";
    }

    @GetMapping("/product_detail/{id}")
    public String productDetail(@PathVariable("id")Long id, HttpSession session, Model model){
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        TravelPlans selectedPlan = travelPlansService.findByPlanId(id);
        List<Comments> comments = commentsController.getComments(id);
        List<PlanDetail> planDetails = planDetailRepository.findAllByTravelPlanOrderByPlanDateAscPlanTimeAsc(selectedPlan);
        List<Tags> planTags=tagsService.findTags(selectedPlan);
        boardService.increaseViewCount(selectedPlan);
        session.setAttribute("selectedPlan",selectedPlan);
        model.addAttribute("selectedPlan", selectedPlan);
        model.addAttribute("user", user);
        model.addAttribute("comments",comments);
        model.addAttribute("tagsList", planTags);

        boolean alreadyPurchased = ordersItemRepository.existsByOrders_UserAndTravelPlans(user, selectedPlan);
        if (alreadyPurchased || selectedPlan.getUser().getEmail().equals(session.getAttribute("user"))) {
            model.addAttribute("planDetails", planDetails);
        }
        return "/productDetail";
    }

    @GetMapping("/qnaBoard")
    public String showQnaBoard(Model model){
        List<Questions> questionsList = qnAService.questionsList();
        List<Answers> answersList =qnAService.answersList();
        model.addAttribute("answerList", answersList);
        model.addAttribute("questionsList", questionsList);

        return "qNa_list";
    }
    @PostMapping("/qnaPost")
    public String postQuestion(QuestionsDTO questionsDTO, HttpSession session){
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        qnAService.postQuestion(questionsDTO,user);
        return "redirect:/qnaBoard";
    }

    @PostMapping("/postAnswer")
    public String postanswer(HttpSession session, AnswersDTO answersDTO){
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        qnAService.addAnswer(answersDTO.getQuestionsId(),answersDTO.getContents(),user);
        return "redirect:/qnaBoard";
    }

    @PostMapping("/updateQuestion")
    public String updateQuestion(@RequestParam Long questionsId, @RequestParam String title, @RequestParam String contents, HttpSession session) {
        String userEmail = (String) session.getAttribute("user");
        Users user = userService.findByEmail(userEmail);

        if (questionsId == null) {
            return "redirect:/qnaBoard";
        }

        try {
            QuestionsDTO questionsDTO = new QuestionsDTO();
            questionsDTO.setQuestionsId(questionsId);
            questionsDTO.setTitle(title);
            questionsDTO.setContents(contents);

            qnAService.updateQuestion(questionsId, questionsDTO, user);
            return "redirect:/qnaBoard";
        } catch (NoSuchElementException e) {
            return "error/404";
        }
    }

    @PostMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam Long questionsId, HttpSession session) {
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        qnAService.deleteQuestion(questionsId, user);
        return "redirect:/qnaBoard";
    }

    @PostMapping("/updateAnswer")
    public String updateAnswer(@RequestParam Long answerId, @RequestParam String content, HttpSession session) {
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        qnAService.updateAnswer(answerId, content, user);
        return "redirect:/qnaBoard";
    }

    @PostMapping("/deleteAnswer")
    public String deleteAnswer(@RequestParam Long answerId, HttpSession session) {
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        qnAService.deleteAnswer(answerId, user);
        return "redirect:/qnaBoard";
    }




}
