package com.yasser.ecommerce.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/api/v1/customer/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") Long customerId);
}
