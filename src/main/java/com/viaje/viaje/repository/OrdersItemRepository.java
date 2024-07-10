package com.viaje.viaje.repository;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersItemRepository extends JpaRepository<OrderItems,Long> {
    List<OrderItems> findAllByOrders(Orders orders);
}
