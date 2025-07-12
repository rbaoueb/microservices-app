package com.example.customerservice.controller;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired private CustomerRepository repo;

    @GetMapping
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return repo.save(customer);
    }
}