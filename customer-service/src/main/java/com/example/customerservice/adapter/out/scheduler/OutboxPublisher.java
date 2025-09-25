package com.example.customerservice.adapter.out.scheduler;

import com.example.customerservice.adapter.out.persistance.repository.CustomerOutboxRepository;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.domain.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxPublisher {

    private final CustomerOutboxRepository customerOutboxRepository;
    private final CustomerEventPublisherOut customerEventPublisherOut;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishOutboxEvents() {
        var events = customerOutboxRepository.findBySentFalse();
        for (var event : events) {
            try {
                log.info("Publishing event " + event.getId());
                customerEventPublisherOut.publishCustomerCreated(objectMapper.convertValue(event.getPayload(), Customer.class));
                event.setSent(true);
                customerOutboxRepository.save(event);
            } catch (Exception e) {
                log.warn("Kafka publish failed for event " + event.getId());
                // retry next scheduled run
            }
        }
    }

}
