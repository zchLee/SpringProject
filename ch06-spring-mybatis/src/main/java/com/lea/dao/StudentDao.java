package com.lea.dao;

import com.lea.domain.Student;

import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 11:03
 */
public interface StudentDao {

    int insertStudent(Student student);

    List<Student> selectStudents();
}
