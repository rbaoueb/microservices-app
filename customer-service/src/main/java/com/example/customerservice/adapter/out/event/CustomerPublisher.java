package com.example.customerservice.adapter.out.event;

import com.example.avro.CustomerEvent;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPublisher implements CustomerEventPublisherOut {

    private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;

    @Value("${app.kafka.customer.topic}")
    private String topic;

    @Override
    public void publishCustomerCreated(Customer customer) {
        CustomerEvent event = CustomerEvent.newBuilder()
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setEventType("CREATED")
                .build();
        kafkaTemplate.send(topic, String.valueOf(customer.getId()), event);
    }

    @Override
    public void publishUpdateEmailCustomer(Customer customer) {
        CustomerEvent customerEvent = CustomerEvent.newBuilder()
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setEventType("UPDATE EMAIL")
                .build();
        kafkaTemplate.send(topic, String.valueOf(customer.getId()), customerEvent);
    }


}
