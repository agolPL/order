package com.redcar.order.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestClientConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
