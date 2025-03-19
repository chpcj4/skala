package com.sk.skala.myapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sk.skala.myapp.model.Stock;

@Component
public class MyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Stock.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Stock stock = (Stock) target;
        if (stock.getStockName() == null || stock.getStockName().trim().isEmpty()) {
            errors.rejectValue("stockName", "null", "stockName은 빈값이어서는 안됩니다.");
        }

        if (stock.getStockPrice() < 0) {
            errors.rejectValue("stockPrice", "too.small", "stockPrice는 0보다 커야 합니다.");
        }
    }
}