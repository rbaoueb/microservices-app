package com.example.customerservice.domain.usecase;

import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;

@UseCase
public record CreateCustomerOutboxUseCase(CustomerRepositoryOut customerRepositoryOut,CustomerOutboxOut customerOutboxOut) implements CreateCustomerIn {


    @Override
    public Customer execute(Customer customer) {
        customerRepositoryOut.findById(customer.getId()).ifPresent(c -> {
            throw new CustomerAlreadyExistException("Customer already exists");
        });
        var createdCustomer = customerRepositoryOut.save(customer);//ok
        customerOutboxOut.execute(createdCustomer,"CustomerCreated");//ko
        return createdCustomer;
    }
}
