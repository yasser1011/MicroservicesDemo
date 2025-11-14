package com.yasser.ecommerce.kafka;

import com.yasser.ecommerce.customer.CustomerResponse;
import com.yasser.ecommerce.order.PaymentMethod;
import com.yasser.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        Integer totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}