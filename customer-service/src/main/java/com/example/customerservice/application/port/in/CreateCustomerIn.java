package com.example.customerservice.application.port.in;

import com.example.customerservice.domain.model.Customer;

@FunctionalInterface
public interface CreateCustomerIn {
    Customer execute(Customer customer);
}
