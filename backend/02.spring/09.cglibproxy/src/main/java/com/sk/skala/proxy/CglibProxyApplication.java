package com.sk.skala.proxy;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sk.skala.proxy.service.RealService;

@SpringBootApplication
public class CglibProxyApplication implements CommandLineRunner {

        private final RealService realService;

    public CglibProxyApplication(RealService realService) {
        this.realService = realService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CglibProxyApplication.class, args);
    }

    @Override
    public void run(String... args) {
        realService.doAction();
    }

}
