package com.tesla.cloud.example;

import com.github.pagehelper.PageHelper;
import com.tesla.cloud.example.entity.Student;
import com.tesla.cloud.example.mapper.StudentMapper;
import com.tesla.cloud.example.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testFindById() throws Exception {
        Student student = studentService.getById(1L);
        PageHelper.startPage(1,10);
        Assert.assertEquals("admin", student.getUserName());
        System.out.println("userName: "+student.getUserName()+" || avg: "+student.getScoreAvg());
    }


    @Test
    public void testFindByUserName() throws Exception {
        List<Student> studentList = studentService.queryByUserName("system");
        PageHelper.startPage(1,10);
        Assert.assertNotNull(studentList);
        System.out.println("studentList size: "+ studentList.size());
    }

}
