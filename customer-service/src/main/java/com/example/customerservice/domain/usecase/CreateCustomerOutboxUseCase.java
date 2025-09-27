package com.example.customerservice.domain.usecase;

import com.example.customerservice.adapter.out.camunda.TasklistAdapter;
import com.example.customerservice.adapter.out.rules.CustomerRulesAdapter;
import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;
import org.springframework.http.HttpStatus;

import java.util.Map;

@UseCase
public record CreateCustomerOutboxUseCase(CustomerRepositoryOut customerRepositoryOut, CustomerOutboxOut customerOutboxOut,
                                          CustomerRulesAdapter customerRulesAdapter, TasklistAdapter tasklistAdapter) implements CreateCustomerIn {


    @Override
    public Customer execute(Customer customer) {
        customerRepositoryOut.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new CustomerAlreadyExistException("Customer already exists");
        });
        var filledCustomer = customerRulesAdapter.fillCustomerDiscount(customer);
        var createdCustomer = customerRepositoryOut.save(filledCustomer);//ok
        customerOutboxOut.saveCustomerCreatedEvent(createdCustomer);//ko

//        String taskId = tasklistAdapter.findUserTaskId("Waiting Customer Creation");
//        if (taskId == null) {
//            throw new RuntimeException("Failed to find task for waiting customer creation");
//        }
//        tasklistAdapter.completeUserTask(taskId, Map.of(
//                "firstName", customer.getFirstName(),
//                "lastName", customer.getLastName(),
//                "email", customer.getEmail(),
//                "status", customer.getStatus(),
//                "discount", customer.getDiscount()
//        ));
        return createdCustomer;
    }
}
