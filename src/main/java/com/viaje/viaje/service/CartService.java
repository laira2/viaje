package com.viaje.viaje.service;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.repository.CartItemsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Transactional
    public void addToCart(Cart cart, TravelPlans plan, int quantity) {
        CartItems existingItem = cartItemsRepository.findByCartAndTravelPlans(cart, plan);
        if (existingItem != null) {
            // 이미 존재하는 항목이면 수량만 증가
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemsRepository.save(existingItem);
        } else {
            // 새로운 항목이면 새로 생성
            CartItems newItem = new CartItems();
            newItem.setCart(cart);
            newItem.setTravelPlans(plan);
            newItem.setQuantity(quantity);
            cartItemsRepository.save(newItem);
        }
    }
}
