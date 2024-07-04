package com.viaje.viaje.repository;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartAndTravelPlans(Cart cart, TravelPlans plan);
}
