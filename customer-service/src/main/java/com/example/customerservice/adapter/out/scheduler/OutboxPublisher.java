package com.example.customerservice.adapter.out.scheduler;

import com.example.avro.CustomerEvent;
import com.example.customerservice.adapter.out.persistance.repository.CustomerOutboxRepository;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.util.SerializationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxPublisher {

    private final CustomerOutboxRepository customerOutboxRepository;
    private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;

    @Value("${app.kafka.customer.topic}")
    private String topic;

    private final CustomerEventPublisherOut customerEventPublisherOut = (customer, eventType) -> CustomerEvent.newBuilder()
            .setFirstName(customer.getFirstName())
            .setLastName(customer.getLastName())
            .setEmail(customer.getEmail())
            .setEventType(eventType)
            .build();

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishOutboxEvents() {
        var events = customerOutboxRepository.findBySentFalse();
        for (var event : events) {
            try {
                log.info("Publishing event " + event.getCustomer());
                var customerEvent = customerEventPublisherOut.execute(event.getCustomer(),event.getType());
                kafkaTemplate.send(topic, String.valueOf(event.getCustomer().getId()), customerEvent);
                event.setSent(true);
                customerOutboxRepository.save(event);
            } catch (Exception e) {
                log.warn("Kafka publish failed for event " + event.getId(),e);
                // retry next scheduled run
            }
        }
    }

}
