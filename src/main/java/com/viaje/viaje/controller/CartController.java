package com.viaje.viaje.controller;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.service.CartService;
import com.viaje.viaje.service.TravelPlansService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {

    private final TravelPlansService travelPlansService;
    private final CartService cartService;

    public CartController(TravelPlansService travelPlansService, CartService cartService) {
        this.travelPlansService = travelPlansService;
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public String add_cart(HttpSession session, Long planId ){
        TravelPlans selectedPlan = travelPlansService.findByPlanId(planId);
        Cart cart = cartService.getCart((String)session.getAttribute("user"));
        cartService.addToCart(cart,selectedPlan,1);
        session.setAttribute("cart",cart);
        return "/cart/detail";
    }

    @GetMapping("/cart/detail")
    public List<CartItems> allCartItem (HttpSession session){
        List<CartItems> itemsList = cartService.findAllcartItmes((Cart) session.getAttribute("cart"));
        return itemsList;
    }
}
