package com.example.customerservice.service;

import com.example.avro.CustomerEvent;
import com.example.customerservice.dto.CustomerDTO;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.event.CustomerEventProducer;
import com.example.customerservice.exception.ResourceNotFoundException;
import com.example.customerservice.mapper.CustomerMapper;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerEventProducer eventProducer;

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
//        CustomerEvent event = CustomerEvent.newBuilder()
//                .setFirstName(saved.getFirstName())
//                .setLastName(saved.getLastName())
//                .setEmail(saved.getEmail())
//                .setEventType("CREATED")
//                .build();
//        eventProducer.sendCustomerEvent(event, saved.getId());
        return customerMapper.toDTO(saved);
    }

    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
