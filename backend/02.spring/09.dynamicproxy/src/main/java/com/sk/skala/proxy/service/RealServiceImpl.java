package com.sk.skala.proxy.service;


public class RealServiceImpl implements MyService {
    @Override
    public void doAction() {
        System.out.println("--> Real Service 실행");
    }
}
