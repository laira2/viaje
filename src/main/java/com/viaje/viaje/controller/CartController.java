package com.viaje.viaje.controller;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.service.CartService;
import com.viaje.viaje.service.TravelPlansService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/cart/detail";
    }

    @GetMapping("/cart/detail")
    public String allCartItem (HttpSession session, Model model){
        Cart cart = cartService.getCart((String) session.getAttribute("user"));
        List<CartItems> itemsList = cartService.findAllcartItmes(cart);

        Integer total_price = cartService.getTotalPriceForCart(cart.getCartId());
        model.addAttribute("itemsList", itemsList);
        model.addAttribute("total_price", total_price);
        return "/cart";

    }

    @PostMapping("/cart/remove")
    public String removeCartItem(HttpSession session,
                                 @RequestParam("id") Long id,
                                 @RequestParam("cart_user_email") String email,
                                 RedirectAttributes redirectAttributes) {
        String sessionUserEmail = (String) session.getAttribute("user");
        if (sessionUserEmail != null && sessionUserEmail.equals(email)) {
            try {
                cartService.removeCartItem(id);
                redirectAttributes.addFlashAttribute("message", "Item successfully removed from cart.");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Failed to remove item from cart: " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to remove this item.");
        }
        return "redirect:/cart/detail";
    }

//    @PostMapping("/createOrder")
//    public String createOrder(HttpSession session){
//
//
//    }


}
