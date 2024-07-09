package com.viaje.viaje.service;

import com.viaje.viaje.dto.PlanCertificationDTO;
import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.model.PlanCertification;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.PlanCertificationRepository;
import com.viaje.viaje.repository.TravelPlansRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TravelPlansService {


    private final FileUploadUtil fileUploadUtil;
    private final TravelPlansRepository travelPlansRepository;

    private final BoardService boardService;
    private final UserService userService;
    private final TagsService tagsService;
    private final PlanCertificationRepository planCertificationRepository;
    public TravelPlansService(FileUploadUtil fileUploadUtil, TravelPlansRepository travelPlansRepository, BoardService boardService, UserService userService, TagsService tagsService, PlanCertificationRepository planCertificationRepository) {
        this.travelPlansRepository = travelPlansRepository;
        this.boardService = boardService;
        this.userService = userService;
        this.tagsService = tagsService;
        this.planCertificationRepository = planCertificationRepository;
        this.fileUploadUtil=fileUploadUtil;
    }
    @Transactional
    public TravelPlans createPlan(HttpSession session, TravelPlansDTO tpDTO, PlanCertificationDTO pcDTO) throws IOException {
        String user_email = (String) session.getAttribute("user");
        Users user = userService.findByEmail(user_email);
        if (user == null) {
            throw new IllegalArgumentException("User is required to create a travel plan.");
        }
        List<String> planFilePaths = new ArrayList<>();
        for (MultipartFile planImage : tpDTO.getPlanImages()) {
            String planFileName = fileUploadUtil.saveFile(planImage, true);
            String planFilePath = fileUploadUtil.getPlanUploadDir() + "/" + planFileName;
            planFilePaths.add(planFilePath);
        }

        List<String> certFilePaths = new ArrayList<>();
        for (MultipartFile certImage : pcDTO.getCertImages()) {
            String certFileName = fileUploadUtil.saveFile(certImage, false);
            String certFilePath = fileUploadUtil.getCertUploadDir() + "/" + certFileName;
            certFilePaths.add(certFilePath);
        }

        TravelPlans travelPlans = TravelPlans.builder()
                .startDate(tpDTO.getStartDate())
                .endDate(tpDTO.getEndDate())
                .nation(tpDTO.getNation())
                .title(tpDTO.getTitle())
                .detail(tpDTO.getDetail())
                .imagePaths(planFilePaths)
                .totalBudget(tpDTO.getTotalBudget())
                .user(user)
                .build();

        TravelPlans savedPlan = travelPlansRepository.save(travelPlans);

        PlanCertification planCertification = PlanCertification.builder()
                .travelPlans(savedPlan)
                .certImagePaths(certFilePaths)
                .build();
        planCertificationRepository.save(planCertification);

        tagsService.insertPlanTag(session, travelPlans);
        boardService.postPlan(user, travelPlans);
        return travelPlans;
    }

    public TravelPlans findByPlanId(Long planId){
        TravelPlans plan = travelPlansRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("TravelPlan not found"));

        return plan;
    }
    public String updateTravelPlan(HttpSession session, Long planId, TravelPlansDTO updatedDTO) {
        TravelPlans plan = travelPlansRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("TravelPlan not found"));

        if (plan.getUser() == session.getAttribute("user")) {
            plan.setNation(updatedDTO.getNation());
            plan.setTitle(updatedDTO.getTitle());
            plan.setDetail(updatedDTO.getDetail());
            // 다른 필드들도 필요에 따라 업데이트
            travelPlansRepository.save(plan);
            return "redirect:/";
        }
        else{
            return "redirect:/plans/new";
        }
    }

    public String updateStatus(HttpSession session, Long planId){
        Optional<TravelPlans> plan = travelPlansRepository.findById(planId);
        if (plan.isPresent()){
            return "redirect:/plans/new";
        }else{
            return "redirect:/";
        }
    }

    public String deletePlan(HttpSession session, Long planId) {
        TravelPlans plan = travelPlansRepository.findById(planId)
                .orElseThrow();
        return "plan deleted";
    }





}
