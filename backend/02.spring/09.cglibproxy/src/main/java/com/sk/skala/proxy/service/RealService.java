package com.sk.skala.proxy.service;

import org.springframework.stereotype.Service;

@Service
public class RealService {
    public void doAction() {
        System.out.println("-->Real Service 실행");
    }
}

