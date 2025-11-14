package com.yasser.ecommerce.orderline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
}