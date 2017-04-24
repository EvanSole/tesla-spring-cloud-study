package com.tesla.cloud.example.config.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private PropertyValues dataSourcePropertyValues;

    // 数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到容器中
        targetDataSources.put("dataSource", defaultDataSource);
        DataSourceContextHolder.dataSourceList.add("dataSource");

        // 将自定义数据源添加到容器中
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DataSourceContextHolder.dataSourceList.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);
        logger.info("Dynamic DataSource Registry");
    }


    /***
     * 初始化默认连接(主数据源)
     * @param env
     */
    private void initDefaultDataSource(Environment env) {
        try {
            //读取主数据源
            RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
            Map<String, Object> dsParam = new HashMap<>();
            dsParam.put("type", propertyResolver.getProperty("type"));
            dsParam.put("driver-class-name", propertyResolver.getProperty("driver-class-name"));
            dsParam.put("url", propertyResolver.getProperty("url"));
            dsParam.put("username", propertyResolver.getProperty("username"));
            dsParam.put("password", propertyResolver.getProperty("password"));
            defaultDataSource = DruidDataSourceFactory.createDataSource(dsParam);
            //绑定其它参数
            dataBinder(defaultDataSource, env);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Initialize the dataSource failed." + e);
        }
    }

    /***
     * 初始化自定义连接(自定义数据源)
     * @param env
     */
    private void initCustomDataSources(Environment env) {
        try{
            //读取自定义数据源
            RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
            String dsPrefixs = propertyResolver.getProperty("names");
            for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
                Map<String, Object> subProperties = propertyResolver.getSubProperties(dsPrefix + ".");
                //RelaxedPropertyResolver对数字类型属性读取为Integer需要转换为String
                Map<String, Object> dsParam = new HashMap<>();
                dsParam.put("type", MapUtils.getString(subProperties,"type"));
                dsParam.put("driver-class-name", MapUtils.getString(subProperties,"driver-class-name"));
                dsParam.put("url", MapUtils.getString(subProperties,"url"));
                dsParam.put("username", MapUtils.getString(subProperties,"username"));
                dsParam.put("password", MapUtils.getString(subProperties,"password"));
                DataSource dataSource = DruidDataSourceFactory.createDataSource(dsParam);
                //存放到集合
                customDataSources.put(dsPrefix, dataSource);
                dataBinder(dataSource, env);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Initialize the dataSource failed." + e);
        }
    }

    /***
     * 读取spring.datasource配置下自定义参数
     * 如:连接池初始化大小、连接等待超时时间等
     * @param dataSource
     * @param env
     */
    private void dataBinder(DataSource dataSource, Environment env){
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);
        if(dataSourcePropertyValues == null){
            Map<String, Object> customParam = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<>(customParam);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

}
