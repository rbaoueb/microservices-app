package com.example.customerservice.infrastructure.kie;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieServerConfig {

    private final KieServerProperties properties;

    public KieServerConfig(KieServerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RuleServicesClient ruleServicesClient() {
        // Configuration KIE
        KieServicesConfiguration config =
                KieServicesFactory.newRestConfiguration(properties.getUrl(), properties.getUser(), properties.getPwd());
        config.setMarshallingFormat(MarshallingFormat.JSON);

        KieServicesClient kieClient = KieServicesFactory.newKieServicesClient(config);
        return kieClient.getServicesClient(RuleServicesClient.class);
    }
}