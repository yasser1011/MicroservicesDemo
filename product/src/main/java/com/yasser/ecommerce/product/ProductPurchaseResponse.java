package com.yasser.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseResponse {
    private Long id;
    private String name;
    private String description;
    private Integer priceInCents;
    private Integer quantity;
}
