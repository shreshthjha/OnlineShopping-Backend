package com.shoppe.adminservice.feign;

import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserServiceClient {
    @Override
    public void blockUser(Long userId) {
        // fallback: do nothing or log — service unavailable
    }
}
