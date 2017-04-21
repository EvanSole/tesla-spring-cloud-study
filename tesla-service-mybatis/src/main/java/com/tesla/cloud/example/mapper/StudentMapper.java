package com.tesla.cloud.example.mapper;

import com.tesla.cloud.example.config.mybatis.TeslaMapper;
import com.tesla.cloud.example.entity.Student;

import java.util.List;

public interface StudentMapper extends TeslaMapper<Student> {

    List<Student> queryByUserName(String userName);

    Student getById(Long id);

}
