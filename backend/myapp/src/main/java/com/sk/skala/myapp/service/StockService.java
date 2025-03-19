package com.sk.skala.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.skala.myapp.model.Stock;
import com.sk.skala.myapp.repository.StockRepository;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.getStockList();
    }

    public Stock createStock(Stock stock) {
        stockRepository.getStockList().add(stock);
        stockRepository.saveStockList();
        return stock;
    }
}
