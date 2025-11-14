package com.yasser.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Integer availableQuantity;
    private Integer priceInCents;
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;
}