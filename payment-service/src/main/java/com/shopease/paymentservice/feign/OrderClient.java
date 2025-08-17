package com.shopease.paymentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service")
public interface OrderClient {

    @PutMapping("/orders/{orderId}")
    void updateOrderStatus(@PathVariable("orderId") Long orderId,
                           @RequestParam("status") String status);
}
