package com.tesla.cloud.example.config.mybatis;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;


/***
 * 1.MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer
 * 2.如果你不使用通用Mapper，可以改为org.xxx...
 * 3.由于MapperScannerConfigurer执行的比较早,所以需要添加AutoConfigureAfter注解
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.tesla.cloud.example.mapper");
        Properties properties = new Properties();
        //Pay special attention to here, and don't put TeslaMapper basePackage,
        //also is not the same as other Mapper to be scanned
        properties.setProperty("mappers", TeslaMapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
