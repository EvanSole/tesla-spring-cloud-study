package com.tesla.cloud.example.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源路由分发器，通过AbstractRoutingDataSource路由determineCurrentLookupKey()方法
 * 获取当前线程的DatabaseType
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //Get Routing Context Holder
        DatabaseContextHolder contextHolder = DatabaseContextHolder.getDatabaseContextHolder();
        if (contextHolder != null) {
            String dataSourceName = contextHolder.getDataSourceName();
            DatabaseContextHolder.clearDatabaseContextHolder();
            return dataSourceName;
        }
        return null;
    }
}
