package com.example.customerservice.event;

import com.example.avro.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventProducer {

    private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;

    @Value("${app.kafka.customer.topic}")
    private String topic;

    public CustomerEventProducer(KafkaTemplate<String, CustomerEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCustomerEvent(CustomerEvent event, Long id) {
        kafkaTemplate.send(topic, String.valueOf(id), event);
    }
}