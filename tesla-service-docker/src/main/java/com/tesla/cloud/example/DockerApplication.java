package com.tesla.cloud.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DockerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DockerApplication.class,args);
    }
}
