package com.sk.skala.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.myapp.model.Stock;
import com.sk.skala.myapp.service.StockService;
import com.sk.skala.myapp.validator.MyValidator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class StockController {
    private StockService stockService;
    private final MyValidator myValidator;

    public StockController(StockService stockService, MyValidator myValidator) {
        this.stockService = stockService;
        this.myValidator = myValidator;
    }

    @GetMapping("/stocks")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }
    // 주식 정보 추가
    @PostMapping("/stocks")
        public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        Errors errors = new BindException(stock, "stock");
        myValidator.validate(stock, errors);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Stock newStock = stockService.createStock(stock);
        return new ResponseEntity<>(newStock, HttpStatus.CREATED);
    }
}
