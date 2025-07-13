package com.example.orderservice.feign;

import com.example.orderservice.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${customer.service.url}") // ou via service discovery
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}