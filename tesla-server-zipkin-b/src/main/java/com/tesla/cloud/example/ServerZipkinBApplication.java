package com.tesla.cloud.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServerZipkinBApplication
{

    public static final Logger logger = LoggerFactory.getLogger(ServerZipkinBApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(ServerZipkinBApplication.class, args);
    }


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hello")
    public String callHome(){
        logger.info("hi is being called");
        return "I'm service-zipkin-b";
    }

    @RequestMapping("/world")
    public String info(){
        logger.info("info is being called");
        return restTemplate.getForObject("http://localhost:9496/info", String.class);
    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }


}
