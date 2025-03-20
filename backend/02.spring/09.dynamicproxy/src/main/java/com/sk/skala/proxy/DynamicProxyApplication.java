package com.sk.skala.proxy;

import com.sk.skala.proxy.service.MyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DynamicProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicProxyApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MyService myService) {
        return args -> {
            System.out.println("Running application with proxy...");
            myService.doAction();
        };
    }
}
