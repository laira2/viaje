package com.viaje.viaje.repository;

import com.viaje.viaje.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT SUM(tp.price * ci.quantity) FROM CartItems ci JOIN ci.travelPlans tp WHERE ci.cart.id = :cartId")
    Integer calculateTotalPriceByCartId(@Param("cartId") Long cartId);
}
