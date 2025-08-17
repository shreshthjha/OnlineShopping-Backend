package com.shopease.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.paymentservice.entity.Payment;
import com.shopease.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.initiatePayment(payment));
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updateStatus(@PathVariable Long paymentId,
                                                @RequestParam String status) {
        return ResponseEntity.ok(paymentService.updatePaymentStatus(paymentId, status));
    }

}
