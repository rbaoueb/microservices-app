package com.example.customerservice.domain.usecase;

import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;

@UseCase
public record CreateCustomerUseCase(CustomerRepositoryOut customerRepositoryOut,
                                    CustomerEventPublisherOut customerEventPublisherOut) implements CreateCustomerIn {

    @Override
    public Customer execute(Customer customer) {
        var createdCustomer = customerRepositoryOut.save(customer);
        customerEventPublisherOut.publishCustomerCreated(createdCustomer);
        return createdCustomer;
    }
}
