package com.viaje.viaje.service;

import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.TravelPlansRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TravelPlansService {


    @Value("${file.upload-dir}")
    private String uploadDir;
    private final TravelPlansRepository travelPlansRepository;

    private final BoardService boardService;
    private final UserService userService;
    private final TagsService tagsService;

    public TravelPlansService(TravelPlansRepository travelPlansRepository, BoardService boardService, UserService userService, TagsService tagsService) {
        this.travelPlansRepository = travelPlansRepository;
        this.boardService = boardService;
        this.userService = userService;
        this.tagsService = tagsService;
    }
    @Transactional
    public TravelPlans createPlan(HttpSession session,TravelPlansDTO tpDTO, MultipartFile file) throws IOException {
        String user_email = (String) session.getAttribute("user");
        Users user = userService.findByEmail(user_email);
        if (user == null) {
            throw new IllegalArgumentException("User is required to create a travel plan.");
        }
        String fileName = saveFile(file,user);
        String filePath = "/uploads/" + fileName;

        TravelPlans travelPlans = TravelPlans.builder()
                .startDate(tpDTO.getStartDate())
                .endDate(tpDTO.getEndDate())
                .nation(tpDTO.getNation())
                .title(tpDTO.getTitle())
                .detail(tpDTO.getDetail())
                .fileName(fileName)
                .filePath(filePath)
                .totalBudget(tpDTO.getTotalBudget())
                .user(user)
                .build();

        travelPlansRepository.save(travelPlans);
        tagsService.insertPlanTag(session, travelPlans);
        boardService.postPlan(user,travelPlans);
        return travelPlans;
    }
    private String saveFile(MultipartFile file, Users user) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = user.getUuid()+ "_" + fileName;
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName;
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
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
