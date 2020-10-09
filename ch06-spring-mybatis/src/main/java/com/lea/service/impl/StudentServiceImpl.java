package com.lea.service.impl;

import com.lea.dao.StudentDao;
import com.lea.domain.Student;
import com.lea.service.StudentService;

import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 11:44
 */
public class StudentServiceImpl implements StudentService {

    // 引用类型dao
    private StudentDao studentDao;

    // 使用set注入，赋值
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryStudent() {
        return studentDao.selectStudents();
    }
}
