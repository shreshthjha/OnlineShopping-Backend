package com.shoppe.adminservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallback = UserServiceFallback.class)
public interface UserServiceClient {

    // This assumes User Service exposes an endpoint to block user like: PUT /user/block/{userId}
    @PutMapping("/user/block/{userId}")
    void blockUser(@PathVariable("userId") Long userId);
}
