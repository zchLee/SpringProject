package com.lea.service.impl;

import com.lea.dao.StudentDao;
import com.lea.domain.Student;
import com.lea.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 11:44
 */
@Service
public class StudentServiceImpl implements StudentService {

    // 引用类型dao
    @Autowired
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryStudent() {
        return studentDao.selectStudents();
    }
}
