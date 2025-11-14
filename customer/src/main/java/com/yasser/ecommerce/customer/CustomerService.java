package com.yasser.ecommerce.customer;

import com.yasser.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public Long createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.getId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with id: %s", request.getId())
                ));
        updateCustomerFields(customer, request);
        repository.save(customer);
    }

    private void updateCustomerFields(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.getFirstname())) {
            customer.setFirstname(request.getFirstname());
        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return  repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with id: %s", id)));
    }

    public boolean existsById(Long id) {
        return repository.findById(id)
                .isPresent();
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
