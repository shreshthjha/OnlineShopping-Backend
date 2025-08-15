package com.shoppe.cartservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shoppe.cartservice.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> 
{
    List<Cart> findByUserId(Long userId);
}
