package com.shoppe.cartservice.service;

import java.util.List;
import com.shoppe.cartservice.model.Cart;

public interface CartService 
{
    Cart addCart(Cart cart);
    List<Cart> getCartsByUserId(Long userId);
    Cart updateCart(Long cartId, int quantity);
    void deleteCart(Long cartId);
}
