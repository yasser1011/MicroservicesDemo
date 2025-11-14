package com.yasser.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductPurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Long productId;
    @Positive(message = "Quantity is mandatory")
    private Integer quantity;
}
