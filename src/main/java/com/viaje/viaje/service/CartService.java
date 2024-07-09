package com.viaje.viaje.service;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.CartItemsRepository;
import com.viaje.viaje.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private CartItemsRepository cartItemsRepository;
    private CartRepository cartRepository;
    private UserService userService;

    public CartService(CartItemsRepository cartItemsRepository, CartRepository cartRepository, UserService userService) {
        this.cartItemsRepository = cartItemsRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Transactional
    public void addToCart(Cart cart, TravelPlans plan, int quantity) {
        CartItems existingItem = cartItemsRepository.findByCartAndTravelPlans(cart, plan);
        if (existingItem != null) {

        } else {
            // 새로운 항목이면 새로 생성
            CartItems newItem = new CartItems();
            newItem.setCart(cart);
            newItem.setTravelPlans(plan);
            newItem.setQuantity(quantity);
            cartItemsRepository.save(newItem);
        }
    }

    public Cart getCart(String user_email) {
        Users user = userService.findByEmail(user_email);
        Cart cart = cartRepository.findById(user.getUserId())
                .orElseGet(()->{
                    Cart newCart = new Cart();
                    newCart.setUsers(user);
                    return cartRepository.save(newCart);
                });
        return cart;
    }

    public List<CartItems> findAllcartItmes(Cart cart) {
        return cartItemsRepository.findByCart(cart);

    }

    public void removeCartItem(Long id) {
        cartItemsRepository.deleteById(id);
    }
    public Integer getTotalPriceForCart(Long cartId) {
        Integer totalPrice = cartRepository.calculateTotalPriceByCartId(cartId);
        return totalPrice != null ? totalPrice : 0;
    }
}
