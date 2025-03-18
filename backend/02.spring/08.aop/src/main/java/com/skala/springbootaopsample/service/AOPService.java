package com.skala.springbootaopsample.service;

import org.springframework.stereotype.Service;

@Service
public class AOPService {

    public void doAction() {
        System.out.println("--> Executing performAction method in ExampleService.");
    }
}