package com.example.customerservice.adapter.in.camunda;


import com.example.customerservice.domain.model.Customer;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.internal.command.CommandFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import java.util.ArrayList;
import java.util.List;

public class KieServerClientExample {


    public static void main(String[] args) {

        String serverUrl = "http://localhost:3367/kie-server/services/rest/server";
        String containerId = "customer_rules_1.0.0-SNAPSHOT";

        KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(serverUrl, "kieserver", "kieserver1!");
        config.setMarshallingFormat(MarshallingFormat.JSON);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(config);

        // Créer un client de commandes
        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

        // Créer le Customer
        Customer customer = new Customer();
        customer.setStatus("VIP");
        customer.setEmail("vip@example.com");

        // Préparer les commandes
        List<Command<?>> commands = new ArrayList<>();
        commands.add(CommandFactory.newInsert(customer, "customer")); // <-- out-identifier important
        commands.add(CommandFactory.newFireAllRules());

        BatchExecutionCommand batchCommand = CommandFactory.newBatchExecution(commands, "defaultKieSession");

        ServiceResponse<ExecutionResults> response = ruleClient.executeCommandsWithResults(containerId, batchCommand);

        Customer resultCustomer = (Customer) response.getResult().getValue("customer");

        System.out.println("Discount returned: " + resultCustomer.getDiscount()); // devrait afficher 0.2
    }
}
