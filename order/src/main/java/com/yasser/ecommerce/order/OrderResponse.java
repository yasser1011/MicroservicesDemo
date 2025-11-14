package com.yasser.ecommerce.order;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderResponse {
    private Long id;
    private String reference;
    private Integer amount;
    private PaymentMethod paymentMethod;
    private Long customerId;
}
