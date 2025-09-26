package com.example.customerservice.adapter.in.rest;

import com.example.customerservice.adapter.in.mapper.CustomerRestMapper;
import com.example.customerservice.adapter.in.rest.dto.CreateCustomerRequest;
import com.example.customerservice.adapter.in.transaction.RetryableTransactionalCreateCustomerService;
import com.example.customerservice.adapter.in.transaction.RetryableTransactionalUpdateCustomerService;
import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.domain.usecase.CreateCustomerUseCase;
import com.example.customerservice.domain.model.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

//    private final CreateCustomerIn createCustomerUseCase;
    private final RetryableTransactionalCreateCustomerService retryableCreateCustomerService;
    private final RetryableTransactionalUpdateCustomerService retryableUpdateCustomerService;
    private final CustomerRestMapper mapper;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createRetryable(@RequestBody @Valid CreateCustomerRequest request) {
        log.debug("Request received: create customer");
        Customer customerDomain = mapper.toDomain(request);
        Customer created = retryableCreateCustomerService.create(customerDomain);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomerRetryable(
            @PathVariable("id") Long id,
            @RequestBody CreateCustomerRequest request) {
        log.debug("Request received: update customer");
        Customer customerDomain = mapper.toDomain(request);
        Customer created = retryableUpdateCustomerService.update(id,customerDomain);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
}