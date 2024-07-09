package com.viaje.viaje.service;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.OrderItems;
import com.viaje.viaje.model.Orders;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class OrdersService {
    private CartService cartService;

    private UserService userService;
    public OrdersService(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }



    public List<OrderItems> createOrderItems(HttpSession session,Model model) {
        Orders orders = newOrders((String) session.getAttribute("user"), (Cart) session.getAttribute("cart"));
        List<CartItems> cartItemsList = cartService.findAllcartItmes((Cart) session.getAttribute("cart"));
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
        return newOrders;
    }
}
