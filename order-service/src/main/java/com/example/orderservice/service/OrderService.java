package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.feign.CustomerClient;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.Product;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerClient customerClient) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerClient = customerClient;
    }

    @Transactional
    public OrderDTO getOrderById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        log.error("going to get customer id "+order.getCustomer().getId());
        var customer = customerClient.getCustomerById(order.getCustomer().getId());
        log.error("got customer "+customer);
        var totalPrice = order.getItems().stream().map(OrderItem::getProduct).mapToDouble(Product::getPrice).sum();
        return orderMapper.orderToDto(order, customer, totalPrice);
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = orderMapper.orderToEntity(dto);
        var customer = customerClient.getCustomerById(order.getCustomer().getId());
        Order saved = orderRepository.save(order);
        var totalPrice = order.getItems().stream().map(OrderItem::getProduct).mapToDouble(Product::getPrice).sum();
        return orderMapper.orderToDto(saved, customer, totalPrice);
    }
}
