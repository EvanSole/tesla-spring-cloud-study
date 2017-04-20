package com.tesla.cloud.example.controller;

import com.tesla.cloud.example.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @GetMapping(value = "/hello")
    public String sayHello(@RequestParam String name){
        return ribbonService.helloService(name);
    }

}
