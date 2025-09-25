package com.example.customerservice.infrastructure.zeepe;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ZeepeConfig {

    @Bean
    @Primary
    public ZeebeClient myZeebeClient() {
        return ZeebeClient.newClientBuilder()
                .gatewayAddress("zeebe:26500")
                .usePlaintext()
                .build();
    }
}
