package com.tesla.cloud.example.config.datasource;

import com.tesla.cloud.example.common.DbShareField;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    DbShareField dbShareField() default DbShareField.DEFAULT;

}
