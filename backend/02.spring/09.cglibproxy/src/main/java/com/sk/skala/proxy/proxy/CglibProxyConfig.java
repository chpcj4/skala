package com.sk.skala.proxy.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.sk.skala.proxy.service.RealService;

@Configuration
public class CglibProxyConfig {
    @Bean
    @Primary
    public RealService proxyRealService(RealService realService) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("#### Before method: " + method.getName());
                Object result = proxy.invoke(realService, args);
                System.out.println("#### After method: " + method.getName());
                return result;
            }
        });
        return (RealService) enhancer.create();
    }
}
