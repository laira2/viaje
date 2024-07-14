package com.viaje.viaje.service;

import com.viaje.viaje.model.*;
import com.viaje.viaje.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.viaje.viaje.model.Orders.OrderStatus.*;

@Service
public class OrdersService {
    private CartService cartService;
    private CartItemsRepository cartItemsRepository;

    private UserService userService;
    private UserRepository userRepository;
    private OrdersRepository ordersRepository;
    private OrdersItemRepository ordersItemRepository;
    public OrdersService(CartService cartService, CartItemsRepository cartItemsRepository, UserService userService, UserRepository userRepository, OrdersRepository ordersRepository, OrdersItemRepository ordersItemRepository) {
        this.cartService = cartService;
        this.cartItemsRepository = cartItemsRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
        this.ordersItemRepository = ordersItemRepository;
    }


    @Transactional
    public List<OrderItems> createOrderItems(HttpSession session, Model model) {
        Cart cart = cartService.getCart((String) session.getAttribute("user"));
        Orders order = newOrders((String) session.getAttribute("user"), cart);
        List<CartItems> cartItemsList = cartService.findAllcartItmes(cart);
        List<OrderItems> newOrderItems = new ArrayList<>();

        for (CartItems item : cartItemsList) {
            OrderItems additem = OrderItems.builder()
                    .orders(order)
                    .travelPlans(item.getTravelPlans())
                    .quantity(1)
                    .build();
            newOrderItems.add(ordersItemRepository.save(additem));
        }

        return newOrderItems;
    }

    public Orders newOrders(String currentUser, Cart cart) {
        Orders newOrders = Orders.builder()
                .user(userService.findByEmail(currentUser))
                .total_amount(cartService.getTotalPriceForCart(cart.cartId))
                .build();

        return ordersRepository.save(newOrders);
    }
    @Transactional
    public void payorder(Long orderId, HttpSession session, Model model) {
        Users user = userService.findByEmail((String) session.getAttribute("user"));
        Orders order = ordersRepository.findById(orderId).orElseThrow();
        List<OrderItems> orderItemsList = ordersItemRepository.findAllByOrders(order);

        if (Integer.valueOf(user.getPoint()) >= order.getTotal_amount()){
            user.setPoint(String.valueOf(Integer.valueOf(user.getPoint()) - order.getTotal_amount()));
            for(OrderItems orderItem : orderItemsList){
                Long createPlanUserId = orderItem.getTravelPlans().getUser().getUserId();
                Users seller = userRepository.findById(createPlanUserId).orElseThrow();
                seller.setPoint(seller.getPoint()+orderItem.getTravelPlans().getPrice()*0.1);
                order.setOrderStatus(COMPLETED);

                cartItemsRepository.deleteAllByCart(cartService.getCart(user.getEmail()));
                model.addAttribute("order", order);
            }
        }else{
            order.setOrderStatus(PROCESSING);

        }

    }
}
