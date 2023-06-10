package com.example.cloud.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author create by Tunyl on 2023/5/23
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class TunylServerEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TunylServerEurekaApplication.class);
    }
}
