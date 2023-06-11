package com.example.cloud.aservice.sms;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.example.cloud"})
@EnableEurekaClient
@EnableApolloConfig
@EnableCircuitBreaker
public class AserviceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AserviceSmsApplication.class);
    }
}
