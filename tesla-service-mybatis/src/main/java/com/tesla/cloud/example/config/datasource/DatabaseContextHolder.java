package com.tesla.cloud.example.config.datasource;

import org.springframework.util.Assert;

/***
 * 线程安全的DatabaseType容器，提供DatabaseType设置和获取
 */
public class DatabaseContextHolder {

    private static ThreadLocal<DatabaseContextHolder> contextHolderThreadLocal = new ThreadLocal<DatabaseContextHolder>();

    private String dataSourceName;

    public String getDataSourceName() {
        return dataSourceName;
    }

    public DatabaseContextHolder(String dataSourceName){
        super();
        this.dataSourceName = dataSourceName;
        setDatabaseContextHolder(this);
    }

    public static void setDatabaseContextHolder(DatabaseContextHolder contextHolder){
        Assert.notNull(contextHolder, "contextHolder cannot be null");
        contextHolderThreadLocal.set(contextHolder);
    }

    public static DatabaseContextHolder getDatabaseContextHolder(){
       return contextHolderThreadLocal.get();
    }

    public static void clearDatabaseContextHolder(){
        contextHolderThreadLocal.remove();
    }

}
