package com.yasser.ecommerce.orderline;

import com.yasser.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.getOrderId())
                .productId(request.getProductId())
                .order(
                        Order.builder()
                                .id(request.getOrderId())
                                .build()
                )
                .quantity(request.getQuantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}