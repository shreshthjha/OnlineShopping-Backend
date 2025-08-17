package com.shopease.orderservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.orderservice.entity.Order;
import com.shopease.orderservice.exceptions.OrderNotFoundException;
import com.shopease.orderservice.feign.CartClient;
import com.shopease.orderservice.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private CartClient cartClient;

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        // Feign call -> get cart items of user (just to test microservice communication)
        List<Map<String,Object>> cartItems = cartClient.getCartByUserId(order.getUserId());
        System.out.println("Cart Items (from Feign) = " + cartItems);

        order.setOrderStatus("PENDING");
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}
