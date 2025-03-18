package com.sk.skala.proxy.proxy;

import com.example.proxy.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Configuration
public class JdkProxyConfig {
    @Bean
    public Service proxyService(Service realService) {
        return (Service) Proxy.newProxyInstance(
                realService.getClass().getClassLoader(), // 클래스 로더
                new Class[]{Service.class},      // 구현할 인터페이스 목록
                new InvocationHandler() {     // 메소드 호출시 처리할 내용
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("#### Before method: " + method.getName());
                        Object result = method.invoke(realService, args);
                        System.out.println("#### After method: " + method.getName());
                        return result;
                    }
                }
        );
    }
}

