package com.yasser.ecommerce.payment;

public record PaymentRequest(Long id,
                             Integer amount,
                             PaymentMethod paymentMethod,
                             Long orderId,
                             String orderReference,
                             Customer customer) {
}
