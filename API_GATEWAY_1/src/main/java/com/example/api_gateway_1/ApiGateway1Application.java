package com.example.api_gateway_1;

import com.example.api_gateway_1.configuration.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class ApiGateway1Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateway1Application.class, args);
    }

}
