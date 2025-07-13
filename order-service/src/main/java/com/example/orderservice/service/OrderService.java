package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.exception.ResourceNotFoundException;
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

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDTO getOrderById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        var totalPrice = order.getItems().stream().map(OrderItem::getProduct).mapToDouble(Product::getPrice).sum();
        return orderMapper.orderToDto(order,totalPrice);
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = orderMapper.orderToEntity(dto);
        Order saved = orderRepository.save(order);
        var totalPrice = order.getItems().stream().mapToDouble(OrderItem::getQuantity).sum();
        return orderMapper.orderToDto(saved, totalPrice);
    }
}
