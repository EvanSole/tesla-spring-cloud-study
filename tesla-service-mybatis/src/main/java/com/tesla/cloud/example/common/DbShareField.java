package com.tesla.cloud.example.common;

/**
 * Created by Edwin.wang on 17/4/26.
 */
public enum DbShareField {

    DEFAULT("dataSource"),MAIN("ds1"),ORDER("ds2");

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private DbShareField(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

}
