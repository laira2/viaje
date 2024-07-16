package com.viaje.viaje.controller;

import com.viaje.viaje.model.Board;
import com.viaje.viaje.model.Comments;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.service.AdminService;
import com.viaje.viaje.service.BoardService;
import com.viaje.viaje.service.TravelPlansService;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    public UserService userService;

    @Autowired
    public CommentsController commentsController;

    @Autowired
    public TravelPlansService travelPlansService;

    @Autowired
    public BoardService boardService;

    @GetMapping("/admin")
    public String listPlans(HttpSession session, Model model) {

        String email = (String) session.getAttribute("user");
        Boolean isAdmin = adminService.isAdmin(email);
        if (email != null && adminService.isAdmin(email)) {
            session.setAttribute("isAdmin",isAdmin);
            return "/admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/products/admin")
    public String PlansStatus(Model model) {
        List<Board> pendingPlans = adminService.findPendingTravelPlans();
        model.addAttribute("boardList", pendingPlans);
        return "board";
    }

    @GetMapping("/product_detail/admin/{id}")
    public String productDetailAdmin(@PathVariable("id")Long id, HttpSession session, Model model){
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        TravelPlans selectedPlan = travelPlansService.findByPlanId(id);
        List<Comments> comments = commentsController.getComments(id);

        // 관리자 권한 확인
        if (user != null && user.isAdmin()) {
            session.setAttribute("selectedPlan", selectedPlan);
            model.addAttribute("selectedPlan", selectedPlan);
            model.addAttribute("user", user);
            model.addAttribute("comments", comments);
            return "/productDetail";
        } else {
            // 관리자가 아닌 경우, 다른 페이지로 리다이렉트하거나 권한 없음 메시지를 보여줄 수 있음
            return "redirect:/"; // 예시로 홈 페이지로 리다이렉트하는 것으로 설정
        }

    }

    @PostMapping("/plan/updateStatus")
    public String updateStatus(@RequestParam("planId") Long planId, @RequestParam("status") String status) {
        adminService.updatePlanStatus(planId, status);
        return "redirect:/products/admin";
    }

}
