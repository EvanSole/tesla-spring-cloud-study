package com.tesla.cloud.example.service.impl;

import com.tesla.cloud.example.common.DbShareField;
import com.tesla.cloud.example.config.datasource.TargetDataSource;
import com.tesla.cloud.example.entity.Student;
import com.tesla.cloud.example.mapper.StudentMapper;
import com.tesla.cloud.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @TargetDataSource(dbShareField = DbShareField.DEFAULT)  //使用数据源ds1
    @Override
    public Student getById(Long id) {

        List<Student> list = queryByUserName("system");
        System.out.println("--->"+list.size());
        return studentMapper.getById(id);
    }


    @TargetDataSource(dbShareField = DbShareField.ORDER)  //使用数据源ds2
    @Override
    public List<Student> queryByUserName(String userName) {
        return studentMapper.queryByUserName(userName);
    }



}
