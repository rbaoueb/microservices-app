package com.example.customerservice.adapter.in.rest;

import com.example.customerservice.adapter.in.mapper.CustomerRestMapper;
import com.example.customerservice.adapter.in.rest.dto.CreateCustomerRequest;
import com.example.customerservice.adapter.in.transaction.RetryableTransactionalCreateCustomerService;
import com.example.customerservice.domain.model.Customer;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/workflow", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CamundaController {

//    private final CreateCustomerIn createCustomerUseCase;
    private final RetryableTransactionalCreateCustomerService retryableCreateCustomerService;
    private final CustomerRestMapper mapper;
    private final ZeebeClient myZeebeClient;

//    @GetMapping
//    public ResponseEntity<List<CustomerDTO>> getAll() {
//        log.debug("Request received: list customers");
//        return ResponseEntity.ok(customerService.findAllCustomers());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
//        log.debug("Request received: get customer by id={}", id);
//        return ResponseEntity.ok(customerService.getCustomerById(id));
//    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Customer> create(@RequestBody @Valid CreateCustomerRequest request) {
//        log.debug("Request received: create customer");
//        Customer customerDomain = mapper.toDomain(request);
//        Customer created = createCustomerUseCase.execute(customerDomain);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(created.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(created);
//    }

    @GetMapping("/start_user_task")
    public ResponseEntity<String> createRetryable() {
        ProcessInstanceEvent instance = myZeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("customerDiscountProcess")
                .latestVersion()
                .send()
                .join();

        return ResponseEntity.ok("Started process with key: " + instance.getProcessInstanceKey());

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path = "/start_basic")
    public ResponseEntity<CreateCustomerRequest> start_basic_flow(@RequestBody @Valid CreateCustomerRequest customer) {
        ProcessInstanceEvent event = myZeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("customerProcess")
                .latestVersion()
                .variables(Map.of(
                        "firstName", customer.firstName(),
                        "lastName", customer.lastName(),
                        "email", customer.email(),
                        "status", customer.status()
                ))
                .send()
                .join();

        return ResponseEntity.ok(customer);
    }
}