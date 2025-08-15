package com.shoppe.cartservice.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.shoppe.cartservice.exception.BadRequestException;
import com.shoppe.cartservice.exception.ResourceNotFoundException;
import com.shoppe.cartservice.model.Cart;
import com.shoppe.cartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart updateCart(Long cartId, int quantity) {
        if (quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }
        return cartRepository.findById(cartId)
                .map(c -> {
                    c.setQuantity(quantity);
                    return cartRepository.save(c);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + cartId));
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
