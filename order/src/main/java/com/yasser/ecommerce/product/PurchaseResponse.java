package com.yasser.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PurchaseResponse {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer priceInCents;
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;
}