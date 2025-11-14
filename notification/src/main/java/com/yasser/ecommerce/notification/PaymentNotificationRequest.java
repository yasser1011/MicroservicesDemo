package com.yasser.ecommerce.notification;

import com.yasser.ecommerce.payment.PaymentMethod;

public record PaymentNotificationRequest(
        String orderReference,
        Integer amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}