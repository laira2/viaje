package com.viaje.viaje.repository;
import com.viaje.viaje.model.Cart;
import com.viaje.viaje.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT SUM(tp.price * ci.quantity) FROM CartItems ci JOIN ci.travelPlans tp WHERE ci.cart.id = :cartId")
    Integer calculateTotalPriceByCartId(@Param("cartId") Long cartId);

    Optional<Cart> findByUsers(Users user);
}
