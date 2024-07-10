package com.viaje.viaje.service;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import com.viaje.viaje.repository.OrdersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class OrdersService {
    private CartService cartService;

    private UserService userService;
    private OrdersRepository ordersRepository;
    public OrdersService(CartService cartService, UserService userService, OrdersRepository ordersRepository) {
        this.cartService = cartService;
        this.userService = userService;
        this.ordersRepository = ordersRepository;
    }



    public List<OrderItems> createOrderItems(HttpSession session,Model model) {
        Cart cart = cartService.getCart((String) session.getAttribute("user"));
        Orders orders = newOrders((String) session.getAttribute("user"), cart);
        List<CartItems> cartItemsList = cartService.findAllcartItmes(cart);
        List<OrderItems> orderItemsList = null;
        for (CartItems item :cartItemsList){
            OrderItems additem = OrderItems.builder()
                    .orders(orders)
                    .travelPlans(item.getTravelPlans())
                    .quantity(1)
                    .build();
            orderItemsList.add(additem);
        }
        return orderItemsList;


    }

    public Orders newOrders(String currentUser, Cart cart) {
        Orders newOrders = Orders.builder()
                .user(userService.findByEmail(currentUser))
                .total_amount(cartService.getTotalPriceForCart(cart.cartId))
                .build();

        return ordersRepository.save(newOrders);
    }
}
