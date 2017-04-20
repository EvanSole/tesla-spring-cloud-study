package com.tesla.cloud.example.filter;

/**
 * zuul中定义了四种不同生命周期的过滤器类型,
 * 通过filterType设置
 */
public enum FilterTypeEnum {
    PRE("pre"),          //路由之前
    ROUTING("routing"),  //路由之时
    POST("post"),        //路由之后
    ERROR("error");      //发送错误调用
    private String value;

    FilterTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

}
