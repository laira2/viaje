package com.viaje.viaje.service;

import com.viaje.viaje.model.PlanTag;
import com.viaje.viaje.model.Tags;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.PlanTagRepository;
import com.viaje.viaje.repository.TagsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println("tagOptions: " + Arrays.toString(tagOptions));
        if (tagOptions != null) {
            for (String tagOption : tagOptions) {
                PlanTag plantag = new PlanTag();
                System.out.println("Processing tagOption: " + tagOption);
                plantag.setTags(tagsRepository.findByTagName(tagOption));
                plantag.setTravelPlans(travelPlans);
                planTagRepository.save(plantag);
            }
        }
    }
    public List<Tags> findTags(TravelPlans travelPlans){
        List<PlanTag> planTagsList = planTagRepository.findAllByTravelPlans(travelPlans);
        List<Tags> plantags = new ArrayList<>();
        for (PlanTag plantag : planTagsList){
            plantags.add(plantag.getTags());
        }
        return plantags;
    }
}
