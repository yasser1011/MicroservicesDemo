package com.yasser.ecommerce.kafka;

public record Customer(
        Long id,
        String firstname,
        String lastname,
        String email
) {

}