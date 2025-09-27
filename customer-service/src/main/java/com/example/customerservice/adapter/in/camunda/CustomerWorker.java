package com.example.customerservice.adapter.in.camunda;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
@Slf4j
public class CustomerWorker {


    @JobWorker(type = "create-customer")
    public Map<String, Object> handleCreateCustomer(final ActivatedJob job) {
        Map<String, Object> vars = job.getVariablesAsMap();
        log.error("Vars: {}", vars);

        return Map.of(
                "createdAt", Instant.now().toString()
        );
    }
}
