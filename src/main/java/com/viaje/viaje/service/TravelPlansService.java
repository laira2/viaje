package com.viaje.viaje.service;

import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.TravelPlansRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class TravelPlansService {

    private final TravelPlansRepository travelPlansRepository;

    public TravelPlansService(TravelPlansRepository travelPlansRepository) {
        this.travelPlansRepository = travelPlansRepository;
    }

    public void postPlan(TravelPlans travelPlans) {

        travelPlansRepository.save(travelPlans);
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
