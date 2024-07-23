package com.viaje.viaje.repository;

import com.viaje.viaje.model.Like;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long > {

    boolean existsByUsersAndTravelPlans(Users user, TravelPlans selectedPlan);

    Optional<Like> findByUsersAndTravelPlans(Users users, TravelPlans travelPlans);
}
