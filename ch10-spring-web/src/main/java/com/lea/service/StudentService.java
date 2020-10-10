package com.lea.service;

import com.lea.domain.Student;

import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 11:43
 */
public interface StudentService {

    int addStudent(Student student);

    List<Student> queryStudent();
}
