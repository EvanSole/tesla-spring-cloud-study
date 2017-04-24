package com.tesla.cloud.example.service.impl;

import com.tesla.cloud.example.config.datasource.TargetDataSource;
import com.tesla.cloud.example.entity.Student;
import com.tesla.cloud.example.mapper.StudentMapper;
import com.tesla.cloud.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @TargetDataSource(value="ds1")  //使用数据源ds1
    @Override
    public Student getById(Long id) {
        return studentMapper.getById(id);
    }
}
