package com.viaje.viaje.service;

import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.TagsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class TagsService {
    private final TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public void insertPlanTag(HttpSession session, TravelPlans travelPlans){
        String [] tagOptions = (String[]) session.getAttribute("tagsOptions");
        if (tagOptions != null){

        }

    }
}
