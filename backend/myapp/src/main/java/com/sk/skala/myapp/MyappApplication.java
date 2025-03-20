package com.sk.skala.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sk.skala.myapp.service.AOPService;
import com.sk.skala.myapp.service.SkalaStockMarket;
import com.sk.skala.myapp.controller.AOPController;

@SpringBootApplication
public class MyappApplication implements CommandLineRunner {
    private final AOPService aopService;
    private final AOPController aopController;

    public MyappApplication(AOPService aopService, AOPController aopController) {
        this.aopService = aopService;
        this.aopController = aopController;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        aopService.doAction(); // AOPService 실행
        aopController.hello(); // AOPController 실행
    }

    @Bean
    public CommandLineRunner skalaStockMarketRunner(SkalaStockMarket skalaStockMarket) {
        return args -> {
            skalaStockMarket.start();
        };
    }
}
