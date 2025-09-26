package com.example.customerservice.adapter.out.rules;

import com.example.customerservice.application.port.out.CustomerRulesOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.kie.KieServerProperties;
import lombok.AllArgsConstructor;

import org.kie.api.KieServices;
import org.kie.api.command.KieCommands;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.*;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.ExecutionResults;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class CustomerRulesAdapter implements CustomerRulesOut {

    private final RuleServicesClient ruleClient;
    private final KieServerProperties properties;

    @Override
    public Customer fillCustomerDiscount(Customer customer) {

        KieCommands commandsFactory = KieServices.Factory.get().getCommands();
        Command<?> insert = commandsFactory.newInsert(customer, customer.getEmail());
        Command<?> fire = commandsFactory.newFireAllRules();

        BatchExecutionCommand batch = commandsFactory.newBatchExecution(
                Arrays.asList(insert, fire),
                properties.getSessionName()
        );

        ServiceResponse<ExecutionResults> response =
                ruleClient.executeCommandsWithResults(properties.getContainerId(), batch);

        if (response.getType() == ServiceResponse.ResponseType.SUCCESS) {
            return (Customer) response.getResult().getValue(customer.getEmail());
        } else {
            throw new RuntimeException("Erreur KIE Server : " + response.getMsg());
        }

    }
}
