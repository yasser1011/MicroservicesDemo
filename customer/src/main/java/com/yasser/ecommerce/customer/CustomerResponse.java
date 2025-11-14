package com.yasser.ecommerce.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
}
