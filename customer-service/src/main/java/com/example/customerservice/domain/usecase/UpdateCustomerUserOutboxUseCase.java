package com.example.customerservice.domain.usecase;

import com.example.customerservice.application.exception.ResourceNotFoundException;
import com.example.customerservice.application.port.in.UpdateCustomerPortIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;

import java.util.Optional;

@UseCase
public record UpdateCustomerUserOutboxUseCase(CustomerRepositoryOut customerRepositoryOut,
                                              CustomerOutboxOut customerOutboxOut) implements UpdateCustomerPortIn {

    @Override
    public Customer execute(Long id, Customer customer) {
        Optional<Customer> customerFoundOptional = customerRepositoryOut.findById(id);

        if (customerFoundOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found");
        }

        Customer customerFound = setNewValueForUpdate(customer, customerFoundOptional.get());

        var updatedCustomer = customerRepositoryOut.save(customerFound);
        customerOutboxOut.execute(updatedCustomer, "CustomerUpdated");
        return updatedCustomer;
    }

    private static Customer setNewValueForUpdate(Customer customer, Customer customerFound) {

        if (customerFound.getEmail() != null && !customerFound.getEmail().isEmpty()) {
            customerFound.setEmail(customer.getEmail());
        }

        if (customerFound.getFirstName() != null && !customerFound.getFirstName().isEmpty()) {
            customerFound.setFirstName(customer.getFirstName());
        }

        if (customerFound.getLastName() != null && !customerFound.getLastName().isEmpty()) {
            customerFound.setLastName(customer.getLastName());
        }
        return customerFound;
    }
}
