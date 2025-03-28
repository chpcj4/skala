package com.skala.springbootaopsample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AOPService {

    public void doAction() {
        log.info("###### doAction Call");
        System.out.println("--> Executing performAction method in ExampleService.");
    }
}