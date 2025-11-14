package com.yasser.ecommerce.order;

import com.yasser.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderRequest {
    private Long id;
    private String reference;
    @Positive(message = "Order amount should be positive")
    private Integer amount;
    @NotNull(message = "Payment method should be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Customer should be present")
    private Long customerId;
    @NotEmpty(message = "You should at least purchase one product")
    private List<PurchaseRequest> products;
}
