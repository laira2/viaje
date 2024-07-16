package com.viaje.viaje.controller;

import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import com.viaje.viaje.model.PointTransaction;
import com.viaje.viaje.repository.OrdersItemRepository;
import com.viaje.viaje.repository.OrdersRepository;
import com.viaje.viaje.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrdersController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final OrdersService ordersService;
    private final OrdersRepository ordersRepository;
    private final OrdersItemRepository ordersItemRepository;
    public OrdersController(OrdersService ordersService, OrdersRepository ordersRepository, OrdersItemRepository ordersItemRepository) {
        this.ordersService = ordersService;
        this.ordersRepository = ordersRepository;
        this.ordersItemRepository = ordersItemRepository;
    }

    @PostMapping("/order")
    public String orderPage (HttpSession session, Model model){
        List<OrderItems> newOrderList = ordersService.createOrderItems(session,model);
        model.addAttribute("orderItemList", newOrderList);
        return "/orderPage";
    }

    @PostMapping("/order/create")
    public String createOrders(@RequestParam Long orderId, HttpSession session, Model model){
        ordersService.payorder(orderId,session,model);
        Orders ordered = ordersRepository.findById(orderId).get();
        if (ordered.getOrderStatus().equals(Orders.OrderStatus.COMPLETED)) {
            return "OrderComplete";
        } else if (ordered.getOrderStatus().equals(Orders.OrderStatus.PROCESSING)) {
            model.addAttribute("error","포인트가 부족합니다.");
            return "orderPage";
        }else{
            return "cart";
        }

    }

    @GetMapping("/adminOrderHistory")
    public String adminOrderHistory(Model model) {
        List<OrderItems> ordersItemList = ordersItemRepository.findAll();
        model.addAttribute("ordersItemList", ordersItemList);
        return "orderHistory";
    }
}
