package com.sk.skala.myapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sk.skala.myapp.service.SkalaStockMarket;

@SpringBootApplication
public class MyappApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner skalaStockMarketRunner(SkalaStockMarket skalaStockMarket) {
		return args -> {
			skalaStockMarket.start();
		};
	}
}
