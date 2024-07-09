package com.viaje.viaje.controller;

import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import com.viaje.viaje.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrdersController {

    private OrdersService ordersService;
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @GetMapping("/order")
    public String orderPage (){
        return "testOrder";
    }

    @PostMapping("/order/create")
    public String createOrders(HttpSession session, Model model){
        List<OrderItems> newOrderList = ordersService.createOrderItems(session,model);
        return "OrderComplete";
    }
}
