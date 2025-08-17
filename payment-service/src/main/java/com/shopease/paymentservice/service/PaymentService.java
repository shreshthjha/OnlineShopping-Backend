package com.shopease.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.paymentservice.entity.Payment;
import com.shopease.paymentservice.exceptions.PaymentNotFoundException;
import com.shopease.paymentservice.feign.OrderClient;
import com.shopease.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderClient orderClient;

    public Payment initiatePayment(Payment payment) {
        payment.setStatus("INITIATED");
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(Long paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));

        // update payment table
        payment.setStatus(status);
        paymentRepository.save(payment);

        // also update order-service
        if(status.equalsIgnoreCase("SUCCESS") || status.equalsIgnoreCase("PAID")) {
            orderClient.updateOrderStatus(payment.getOrderId(), "PAID");
        }

        return payment;
    }


}
