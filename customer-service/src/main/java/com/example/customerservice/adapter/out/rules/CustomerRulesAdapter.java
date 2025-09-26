package com.example.customerservice.adapter.out.rules;

import com.example.customerservice.application.port.out.CustomerRulesOut;
import com.example.customerservice.domain.model.Customer;
import lombok.AllArgsConstructor;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.internal.command.CommandFactory;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.kie.server.api.model.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Service
@AllArgsConstructor
public class CustomerRulesAdapter implements CustomerRulesOut {

//    private final KieServicesClient kieServicesClient;

    @Override
    public Customer checkIfCustomerIsAdult(Customer customer) {
//        RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
//
//        // Créer les commandes
//        Command<?> insertCommand = CommandFactory.newInsert(customer, customer.getEmail());
//        Command<?> fireAllRules = CommandFactory.newFireAllRules();
//
//        // Exécuter les commandes sur le container
//        // Créer un batch de commandes
//        BatchExecutionCommand batchCommand = CommandFactory.newBatchExecution(
//                Arrays.asList(insertCommand, fireAllRules),
//                "com.example.rules" // packageName optionnel, peut être null
//        );
//
//        // Exécuter le batch sur le container
//        ServiceResponse<ExecutionResults> response = rulesClient.executeCommandsWithResults(
//                "DemoRules_1.0.0", // containerId
//                batchCommand
//        );
//
//        if (response.getType() == ServiceResponse.ResponseType.SUCCESS) {
//            return (Customer) response.getResult().getValue(customer.getEmail());
//        } else {
//            throw new RuntimeException("Erreur KIE Server: " + response.getMsg());
//        }
        return null;
    }
}
