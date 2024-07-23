package com.viaje.viaje.service;

import com.viaje.viaje.model.Like;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public boolean isLikedByUser(Users user, TravelPlans selectedPlan) {
        return likeRepository.existsByUsersAndTravelPlans(user,selectedPlan);
    }

    public boolean toggleLike(TravelPlans travelPlans, Users users) {
        Optional<Like> existingLike = likeRepository.findByUsersAndTravelPlans(users, travelPlans);
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false;
        } else {
            Like newLike = new Like(users, travelPlans);
            likeRepository.save(newLike);
            return true;
        }
    }
}
