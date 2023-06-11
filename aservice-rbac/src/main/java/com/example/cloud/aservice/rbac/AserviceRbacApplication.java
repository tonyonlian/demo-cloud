package com.example.cloud.aservice.rbac;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.example.cloud.aservice.rbac.config.CustomLoadBanlanceRule;
import com.netflix.loadbalancer.IRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */

@SpringBootApplication(scanBasePackages = {"com.example.cloud"})
@MapperScan(basePackages = {"com.example.cloud.**.mapper"})
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients
@EnableApolloConfig
@EnableCircuitBreaker
public class AserviceRbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(AserviceRbacApplication.class);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // @Bean
    // public IRule lbRule() {
    //     return new CustomLoadBanlanceRule(); //自定义负载均衡规则
    // }
}
