package com.example.orderservice.client;

import com.example.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerClient {
    @GetMapping("/customers")
    List<Customer> getCustomers();
}