package com.tesla.cloud.example.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-client")
public interface FeignService {

    @GetMapping(value = "/hello")
    String sayHello(@RequestParam(value = "name") String name);

}
