package com.example.customerservice.domain.usecase;

import com.example.customerservice.adapter.out.rules.CustomerRulesAdapter;
import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;

@UseCase
public record CreateCustomerOutboxUseCase(CustomerRepositoryOut customerRepositoryOut, CustomerOutboxOut customerOutboxOut,
                                          CustomerRulesAdapter customerRulesAdapter) implements CreateCustomerIn {


    @Override
    public Customer execute(Customer customer) {
        customerRepositoryOut.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new CustomerAlreadyExistException("Customer already exists");
        });
        var filledCustomer = customerRulesAdapter.fillCustomerDiscount(customer);
        var createdCustomer = customerRepositoryOut.save(filledCustomer);//ok
        customerOutboxOut.saveCustomerCreatedEvent(createdCustomer);//ko
        return createdCustomer;
    }
}
