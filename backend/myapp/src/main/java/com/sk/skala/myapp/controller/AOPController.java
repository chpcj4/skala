package com.sk.skala.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aop")
public class AOPController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from AOPController!";
    }
}

