package com.example.customerservice.infrastructure.kie;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kie.server")
@Data
public class KieServerProperties {

    private String url;
    private String user;
    private String pwd;
    private String containerId;
    private String sessionName;

}
