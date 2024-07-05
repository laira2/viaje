package com.viaje.viaje.repository;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartAndTravelPlans(Cart cart, TravelPlans plan);

    List<CartItems> findByCart(Cart cart);
}
