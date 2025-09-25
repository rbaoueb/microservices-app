package com.example.customerservice.domain.usecase;

import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCustomerOutboxUseCase implements CreateCustomerIn {

    private final CustomerRepositoryOut customerRepositoryOut;
    private final CustomerOutboxOut customerOutboxOut;

    @Override
    public Customer execute(Customer customer) {
        var createdCustomer = customerRepositoryOut.save(customer);
        customerOutboxOut.saveCustomerCreatedEvent(createdCustomer);
        return createdCustomer;
    }
}
