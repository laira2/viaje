package com.viaje.viaje.repository;

import com.viaje.viaje.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemRepository extends JpaRepository<OrderItems,Long> {
}
