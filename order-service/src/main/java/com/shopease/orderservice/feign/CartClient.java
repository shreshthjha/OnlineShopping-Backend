package com.shopease.orderservice.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/cart/{userId}")
    List<Map<String, Object>> getCartByUserId(@PathVariable("userId") Long userId);

}
