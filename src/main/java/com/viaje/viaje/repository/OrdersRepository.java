package com.viaje.viaje.repository;

import com.viaje.viaje.model.Orders;
import com.viaje.viaje.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    List<Orders> findAllByUser(Users user);

    List<Orders> findAllByUserAndOrderStatus(Users user, Orders.OrderStatus orderStatus);

    List<Orders> findAllByOrderStatus(Orders.OrderStatus orderStatus);
}
