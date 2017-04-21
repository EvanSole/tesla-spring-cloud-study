package com.tesla.cloud.example.config.swagger;

import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
       return null;
    }

}
