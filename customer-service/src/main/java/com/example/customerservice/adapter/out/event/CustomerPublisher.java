package com.example.customerservice.adapter.out.event;

import com.example.avro.CustomerEvent;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("CUSTOMER_STATUS", "CREATED".getBytes()));
        ProducerRecord <String, CustomerEvent> record = new ProducerRecord<>(topic, null,  String.valueOf(customer.getId()), event, headers);
        kafkaTemplate.send(record);
    }
}
