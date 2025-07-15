package com.example.orderservice.domain.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.example.orderservice", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class))
public class DomainConfig {}