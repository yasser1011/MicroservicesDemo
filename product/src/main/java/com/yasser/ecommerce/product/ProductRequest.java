package com.yasser.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductRequest {
    private Long id;
    @NotNull(message = "Product name is required")
    private String name;
    @NotNull(message = "Product description is required")
    private String description;
    @Positive(message = "Available quantity should be positive")
    private Integer availableQuantity;
    @Positive(message = "Price should be positive")
    private Integer priceInCents;
    @NotNull(message = "Product category is required")
    private Long categoryId;
}
