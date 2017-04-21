package com.tesla.cloud.example.entity;

import java.io.Serializable;

public class Student implements Serializable {

    private Long id;

    private String userName;

    private String scoreSum;

    private String scoreAvg;

    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(String scoreSum) {
        this.scoreSum = scoreSum;
    }

    public String getScoreAvg() {
        return scoreAvg;
    }

    public void setScoreAvg(String scoreAvg) {
        this.scoreAvg = scoreAvg;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
