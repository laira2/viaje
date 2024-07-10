package com.viaje.viaje.controller;

import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import com.viaje.viaje.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrdersController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final OrdersService ordersService;
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/order")
    public String orderPage (HttpSession session, Model model){
        List<OrderItems> newOrderList = ordersService.createOrderItems(session,model);
        model.addAttribute("orderItemList", newOrderList);
        double totalPrice = newOrderList.stream()
                .mapToDouble(item -> item.getTravelPlans().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("total_price", totalPrice);
        return "/test_order";
    }

    @PostMapping("/order/create")
    public String createOrders(HttpSession session, Model model){

        return "OrderComplete";
    }
}
