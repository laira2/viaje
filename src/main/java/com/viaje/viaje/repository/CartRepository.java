package com.viaje.viaje.repository;

import com.viaje.viaje.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
