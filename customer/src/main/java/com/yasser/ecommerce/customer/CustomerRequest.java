package com.yasser.ecommerce.customer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerRequest {
    private Long id;
    @NotNull(message = "firstname required")
    private String firstname;
    @NotNull(message = "lastname required")
    private String lastname;
    @NotNull(message = "email required")
    private String email;
    @NotNull(message = "address required")
    private String address;
}
