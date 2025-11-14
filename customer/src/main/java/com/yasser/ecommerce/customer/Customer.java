package com.yasser.ecommerce.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;

}
