package com.example.orderservice.controller;

import com.example.orderservice.client.CustomerClient;
import com.example.orderservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired private CustomerClient customerClient;

    @GetMapping("/customers")
    public List<Customer> getCustomersFromCustomerService() {
        return customerClient.getCustomers();
    }
}