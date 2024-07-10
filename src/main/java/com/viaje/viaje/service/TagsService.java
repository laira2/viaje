package com.viaje.viaje.service;

import com.viaje.viaje.model.PlanTag;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.PlanTagRepository;
import com.viaje.viaje.repository.TagsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class TagsService {
    private final TagsRepository tagsRepository;
    private final PlanTagRepository planTagRepository;

    public TagsService(TagsRepository tagsRepository, PlanTagRepository planTagRepository) {
        this.tagsRepository = tagsRepository;
        this.planTagRepository = planTagRepository;
    }

    public void insertPlanTag(HttpSession session, TravelPlans travelPlans){
        String [] tagOptions = (String[]) session.getAttribute("tagsOptions");
        if (tagOptions != null) {
            for (String tagOption : tagOptions) {
                PlanTag plantag = new PlanTag();
                plantag.setTags(tagsRepository.findByTagName(tagOption));
                plantag.setTravelPlans(travelPlans);
                planTagRepository.save(plantag);
            }
        }

    }
}
