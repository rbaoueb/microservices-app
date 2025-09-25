package com.example.customerservice.application.port.out;

import com.example.customerservice.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepositoryOut {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
}