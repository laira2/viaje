package com.viaje.viaje.repository;

import com.viaje.viaje.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersItemRepository extends JpaRepository<OrderItems,Long> {
    List<OrderItems> findAllByOrders(Orders orders);

    Boolean existsByOrdersAndTravelPlans(Orders orders, TravelPlans travelPlans);

    boolean existsByOrders_UserAndTravelPlans(Users users, TravelPlans plan);

    List<OrderItems> findAllByOrders_OrderId(Long id);
}
