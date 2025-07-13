package com.example.orderservice.mapper;

import com.example.orderservice.dto.CustomerDTO;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.OrderItemDTO;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.Product;
import com.example.orderservice.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    private ProductRepository productRepository;

    @Mapping(source = "order.id", target = "id")
    @Mapping(source = "order.date", target = "date")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "totalPrice", target = "totalPrice")
    public abstract OrderDTO orderToDto(Order order, CustomerDTO customer, Double totalPrice);

    @Mapping(source = "item.product.name", target = "product")
    @Mapping(source = "item.product.price", target = "price")
    @Mapping(source = "item.quantity", target = "quantity")
    public abstract OrderItemDTO itemToDto(OrderItem item);

    @Mapping(source = "dto.date", target = "date")
    @Mapping(source = "dto.customer", target = "customer")
    public abstract Order orderToEntity(OrderDTO dto);

    @Mapping(source = "dto.product", target = "product", qualifiedByName = "findProductByName")
    @Mapping(source = "dto.quantity", target = "quantity")
    public abstract OrderItem orderItemToEntity(OrderItemDTO dto);

    @Named("findProductByName")
    Product findProductByName(String product) {
        return productRepository.findByName(product).orElseThrow(() -> new ResourceNotFoundException("Product not found with name " + product));
    }

}
