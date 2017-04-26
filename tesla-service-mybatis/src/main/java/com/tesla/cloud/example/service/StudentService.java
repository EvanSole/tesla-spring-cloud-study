package com.tesla.cloud.example.service;

import com.tesla.cloud.example.entity.Student;

import java.util.List;

public interface StudentService {

    Student getById(Long id);

    List<Student> queryByUserName(String userName);

}
