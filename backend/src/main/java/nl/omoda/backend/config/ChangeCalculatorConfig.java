package nl.omoda.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.omoda.service.ChangeCalculatorService;

@Configuration
public class ChangeCalculatorConfig {

    @Bean
    public ChangeCalculatorService changeCalculatorService() {
        return new ChangeCalculatorService();
    }
}
