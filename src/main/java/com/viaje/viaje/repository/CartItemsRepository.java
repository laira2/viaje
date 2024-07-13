package com.viaje.viaje.repository;

import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.CartItems;
import com.viaje.viaje.model.TravelPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartAndTravelPlans(Cart cart, TravelPlans plan);

    List<CartItems> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);
}
