package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> all() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }
}