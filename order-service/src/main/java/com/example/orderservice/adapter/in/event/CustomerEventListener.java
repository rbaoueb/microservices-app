package com.example.orderservice.adapter.in.event;

import com.example.avro.CustomerEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener {

    @KafkaListener(topics = "customer-events", groupId = "order-group")
    public void listenCustomerEvents(ConsumerRecord<String, CustomerEvent> record) {
        CustomerEvent event = record.value();

        System.out.println("🟢 Received customer event:");
        System.out.println(" - ID: " + record.key());
        System.out.println(" - Name: " + event.getFirstName());
    }
}
