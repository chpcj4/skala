package com.sk.skala.proxy.service;

import org.springframework.stereotype.Service;

@Service
public class RealServiceImpl implements MyService {
    @Override
    public void doSomething() {
        System.out.println("--> Real Service 실행");
    }
}