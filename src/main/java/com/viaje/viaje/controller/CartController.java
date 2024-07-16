package com.viaje.viaje.controller;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.CartItemsRepository;
import com.viaje.viaje.repository.OrdersItemRepository;
import com.viaje.viaje.service.CartService;
import com.viaje.viaje.service.TravelPlansService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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
    private final OrdersItemRepository ordersItemRepository;
    private final CartItemsRepository cartItemsRepository;
    private final CartService cartService;

    public CartController(TravelPlansService travelPlansService, OrdersItemRepository ordersItemRepository, CartItemsRepository cartItemsRepository, CartService cartService) {
        this.travelPlansService = travelPlansService;
        this.ordersItemRepository = ordersItemRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.cartService = cartService;
    }

//    @PostMapping("/cart/add")
//    public String add_cart(HttpSession session, Long planId ){
//        TravelPlans selectedPlan = travelPlansService.findByPlanId(planId);
//        Cart cart = cartService.getCart((String)session.getAttribute("user"));
//        String isAdd = cartService.addToCart(cart,selectedPlan,1);
//        if (isAdd.equals("이미 구매한 계획입니다. 마이페이지를 확인해주세요!")){
//            return "/mypage";
//        }
//        session.setAttribute("cart",cart);
//        return "redirect:/cart/detail";
//    }

    @Transactional
    @PostMapping("/cart/add")
    public String addToCart(HttpSession session, Long planId, TravelPlans plan) {
        Cart cart = cartService.getCart((String) session.getAttribute("user"));
        // 먼저 사용자가 이미 이 계획을 구매했는지 확인
        boolean alreadyPurchased = ordersItemRepository.existsByOrders_UserAndTravelPlans(cart.getUsers(), plan);
        if (alreadyPurchased) {
            return "redirect:/mypage";
        }

        // 카트에 이미 존재하는 아이템인지 확인
        CartItems existingItem = cartItemsRepository.findByCartAndTravelPlans(cart, plan);
        if (existingItem != null) {
            return "/cart/detail";
        } else {
            // 새로운 항목이면 새로 생성
            CartItems newItem = new CartItems();
            newItem.setCart(cart);
            newItem.setTravelPlans(plan);
            cartItemsRepository.save(newItem);
        }

        return "/cart/detail";
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
