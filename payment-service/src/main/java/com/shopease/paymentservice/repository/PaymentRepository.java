package com.shopease.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopease.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
