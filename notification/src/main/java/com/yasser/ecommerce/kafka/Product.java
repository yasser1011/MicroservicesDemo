package com.yasser.ecommerce.kafka;


public record Product(
        Integer id,
        String name,
        String description,
        Integer priceInCents,
        Integer quantity
) {
}