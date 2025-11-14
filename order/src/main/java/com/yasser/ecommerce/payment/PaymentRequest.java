package com.yasser.ecommerce.payment;

import com.yasser.ecommerce.customer.CustomerResponse;
import com.yasser.ecommerce.order.PaymentMethod;

public record PaymentRequest(
        Integer amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
