package com.example.orderservice.adapter.in.rest;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.port.in.CreateOrderPort;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CreateOrderPort createOrderPort;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderPort.execute(dto));
    }
}