package com.example.customerservice.application.port.in;

import com.example.customerservice.domain.model.Customer;

@FunctionalInterface
public interface UpdateCustomerPortIn {
    Customer execute(Long id,Customer customer);
}
