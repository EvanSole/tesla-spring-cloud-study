package com.tesla.cloud.example;


import com.tesla.cloud.example.config.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@Import({DynamicDataSourceRegister.class})
public class SampleApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(SampleApplication.class, args);
    }
}
