package com.example.customerservice.domain.usecase;

import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;
import org.apache.commons.lang3.StringUtils;

@UseCase
public record UpdateCustomerUseCase(CustomerRepositoryOut customerRepositoryOut,
                                    CustomerEventPublisherOut customerEventPublisherOut) implements CreateCustomerIn {

    @Override
    public Customer execute(Customer customer) {
        customerRepositoryOut.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new CustomerAlreadyExistException("Customer already exists");
        });
        var updateCustomer = customerRepositoryOut.save(customer);
        customerEventPublisherOut.publishCustomerUpdated(updateCustomer);
        return updateCustomer;
    }
}
