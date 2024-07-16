package com.viaje.viaje.repository;

import com.viaje.viaje.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersItemRepository extends JpaRepository<OrderItems,Long> {
    List<OrderItems> findAllByOrders(Orders orders);

    Boolean existsByOrdersAndTravelPlans(Orders orders, TravelPlans travelPlans);

    boolean existsByOrders_UserAndTravelPlans(Users users, TravelPlans plan);
}
