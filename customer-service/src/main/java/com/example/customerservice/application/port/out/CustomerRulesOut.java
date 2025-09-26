package com.example.customerservice.application.port.out;

import com.example.customerservice.domain.model.Customer;

public interface CustomerRulesOut {
    Customer checkIfCustomerIsAdult(Customer customer);
}
