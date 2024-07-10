package com.viaje.viaje.controller;

import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import com.viaje.viaje.service.OrdersService;
import jakarta.servlet.http.HttpSession;
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

        logger.info("오더 아이템 리스트 정보: {}", ordersService.createOrderItems(session,model));
        model.addAttribute("orderItemList", newOrderList);
        return "/test_order";
    }

    @PostMapping("/order/create")
    public String createOrders(HttpSession session, Model model){

        return "OrderComplete";
    }
}
