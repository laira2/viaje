package com.viaje.viaje.service;

import com.viaje.viaje.model.*;
import com.viaje.viaje.repository.CartItemsRepository;
import com.viaje.viaje.repository.CartRepository;
import com.viaje.viaje.repository.OrdersItemRepository;
import com.viaje.viaje.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private CartItemsRepository cartItemsRepository;
    private CartRepository cartRepository;
    private UserService userService;
    private OrdersItemRepository ordersItemRepository;
    private OrdersRepository ordersRepository;
    public CartService(CartItemsRepository cartItemsRepository, CartRepository cartRepository, UserService userService, OrdersItemRepository ordersItemRepository, OrdersRepository ordersRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.ordersItemRepository = ordersItemRepository;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public String addToCart(Cart cart, TravelPlans plan, int quantity) {
        CartItems existingItem = cartItemsRepository.findByCartAndTravelPlans(cart, plan);
        List<Orders> orderHistory = ordersRepository.findAllByUser(cart.getUsers());
        if (existingItem != null) {
            for (Orders order : orderHistory) {
                boolean alreadyBuy = ordersItemRepository.existsByOrdersAndTravelPlans(order, plan);
                if (alreadyBuy){
                    return "이미 구매한 계획입니다. 마이페이지를 확인해주세요!";
                }
            }
        } else {

            // 새로운 항목이면 새로 생성
            CartItems newItem = new CartItems();
            newItem.setCart(cart);
            newItem.setTravelPlans(plan);
            newItem.setQuantity(quantity);
            cartItemsRepository.save(newItem);

        }
        return "카트에 저장되었습니다";
    }

    public Cart getCart(String user_email) {
        Users user = userService.findByEmail(user_email);
        return cartRepository.findByUsers(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUsers(user);
                    return cartRepository.save(newCart);
                });
    }

    public List<CartItems> findAllcartItmes(Cart cart) {
        return cartItemsRepository.findAllByCart(cart);

    }

    public void removeCartItem(Long id) {
        cartItemsRepository.deleteById(id);
    }
    public Integer getTotalPriceForCart(Long cartId) {
        Integer totalPrice = cartRepository.calculateTotalPriceByCartId(cartId);
        return totalPrice != null ? totalPrice : 0;
    }
}
