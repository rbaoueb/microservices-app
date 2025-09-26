package com.example.customerservice.adapter.out.rules;

import com.example.customerservice.application.port.out.CustomerRulesOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.kie.KieServerProperties;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.command.KieCommands;
import org.kie.internal.command.CommandFactory;
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
@Slf4j
public class CustomerRulesAdapter implements CustomerRulesOut {

    private final RuleServicesClient ruleClient;
    private final KieServerProperties properties;

    @Override
    public Customer fillCustomerDiscount(Customer customer) {

        List<Command<?>> commands = new ArrayList<>();
        commands.add(CommandFactory.newInsert(customer, customer.getEmail()));
        commands.add(CommandFactory.newFireAllRules());
        BatchExecutionCommand batchCommand = CommandFactory.newBatchExecution(commands, "defaultKieSession");

        ServiceResponse<ExecutionResults> response = ruleClient.executeCommandsWithResults(properties.getContainerId(), batchCommand);
        log.error("response : {}",response);
        log.error("response.getType()  : {}",response.getType() );
        log.error("response.getResult().getValue(customer.getEmail())  : {}",response.getResult().getValue(customer.getEmail()));
        if (response.getType() == ServiceResponse.ResponseType.SUCCESS) {
            return (Customer) response.getResult().getValue(customer.getEmail());
        } else {
            throw new RuntimeException("Erreur KIE Server : " + response.getMsg());
        }

    }
}
