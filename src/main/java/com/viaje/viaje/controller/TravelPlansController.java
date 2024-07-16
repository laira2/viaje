package com.viaje.viaje.controller;

import com.viaje.viaje.dto.PlanCertificationDTO;
import com.viaje.viaje.dto.PlanDetailDTO;
import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.TravelPlansRepository;
import com.viaje.viaje.repository.UserRepository;
import com.viaje.viaje.service.FileUploadUtil;
import com.viaje.viaje.service.TravelPlansService;
import com.viaje.viaje.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TravelPlansController {
    @Autowired
    public FileUploadUtil fileUploadUtil;
    public final TravelPlansService travelPlansService;
    public final UserService userService;

    @Autowired
    public TravelPlansController(TravelPlansService travelPlansService, UserService userService){

        this.travelPlansService = travelPlansService;
        this.userService = userService;
    }
    @GetMapping("/createPlan")
    public String createPlanForm(HttpSession session, Model model){
        List<PlanDetailDTO> planDetails = new ArrayList<>();
        planDetails.add(new PlanDetailDTO()); // 초기에 하나의 빈 계획 추가
        model.addAttribute("planDetails", planDetails);
        return "/write";
    }
    @PostMapping("/plan/save")
    public String postPlan(PlanCertificationDTO pcDTO,
                           @RequestParam(value = "tagsOptions", required = false) String[] tagsOptions,
                           HttpSession session,
                           TravelPlansDTO tpDTO,
                           @RequestParam(value = "planDate", required = false) List<LocalDate> planDates,
                           @RequestParam(value = "planTime", required = false) List<LocalTime> planTimes,
                           @RequestParam(value = "activity", required = false) List<String> activities,
                           @RequestParam(value = "description", required = false) List<String> descriptions) throws IOException {
        System.out.println("Received tagsOptions: " + Arrays.toString(tagsOptions));
        List<PlanDetailDTO> planDetails = new ArrayList<>();
        for (int i = 0; i < planDates.size(); i++) {
            PlanDetailDTO dto = new PlanDetailDTO();
            dto.setPlanDate(planDates.get(i));
            dto.setPlanTime(planTimes.get(i));
            dto.setActivity(activities.get(i));
            dto.setDescription(descriptions.get(i));
            planDetails.add(dto);
        }

        session.setAttribute("tagsOptions", tagsOptions);
        TravelPlans created_plan = travelPlansService.createPlan(session, tpDTO, pcDTO, planDetails);
        return "redirect:/product_detail/" + created_plan.getPlanId();
    }

    @PostMapping("/plan/update")
    public String updatePlan(PlanCertificationDTO pcDTO,
                             @RequestParam(value = "tagsOptions", required = false) String[] tagsOptions,
                             HttpSession session,
                             TravelPlansDTO tpDTO,
                             @RequestParam(value = "planDate", required = false) List<LocalDate> planDates,
                             @RequestParam(value = "planTime", required = false) List<LocalTime> planTimes,
                             @RequestParam(value = "activity", required = false) List<String> activities,
                             @RequestParam(value = "description", required = false) List<String> descriptions,
                             @RequestParam(value = "plan", required = true)TravelPlans plan) throws IOException {
        System.out.println("Received tagsOptions: " + Arrays.toString(tagsOptions));
        List<PlanDetailDTO> planDetails = new ArrayList<>();
        for (int i = 0; i < planDates.size(); i++) {
            PlanDetailDTO dto = new PlanDetailDTO();
            dto.setPlanDate(planDates.get(i));
            dto.setPlanTime(planTimes.get(i));
            dto.setActivity(activities.get(i));
            dto.setDescription(descriptions.get(i));
            planDetails.add(dto);
        }

        session.setAttribute("tagsOptions", tagsOptions);
        TravelPlans updated_plan = travelPlansService.updateplan(session, tpDTO, pcDTO, planDetails,plan);
        return "redirect:/product_detail/" + updated_plan.getPlanId();
    }


}
