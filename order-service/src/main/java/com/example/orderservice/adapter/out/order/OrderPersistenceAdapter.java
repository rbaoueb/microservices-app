package com.example.orderservice.adapter.out.order;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.feign.CustomerClient;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.Product;
import com.example.orderservice.port.out.OrderOutPort;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderOutPort {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;

    @Override
    public OrderDTO create(OrderDTO dto) {
        Order order = orderMapper.orderToEntity(dto);
        var customer = customerClient.getCustomerById(order.getCustomer().getId());
        Order saved = orderRepository.save(order);
        var totalPrice = order.getItems().stream().map(OrderItem::getProduct).mapToDouble(Product::getPrice).sum();
        return orderMapper.orderToDto(saved, customer, totalPrice);
    }

    @Override
    public OrderDTO fetchById(Long id) {
        return null;
    }
}
