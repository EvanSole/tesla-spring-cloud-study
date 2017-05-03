package com.tesla.cloud.example.service;


import org.springframework.stereotype.Component;

@Component
public class FeignServiceImpl implements FeignService {
    @Override
    public String sayHello(String name) {
        return "Feign Sorry!";
    }
}
