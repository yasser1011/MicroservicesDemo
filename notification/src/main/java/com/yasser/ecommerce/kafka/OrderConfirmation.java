package com.yasser.ecommerce.kafka;

import com.yasser.ecommerce.payment.PaymentMethod;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        Integer totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}