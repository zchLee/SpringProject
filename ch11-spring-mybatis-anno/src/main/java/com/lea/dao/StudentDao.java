package com.lea.dao;

import com.lea.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzc
 * @create 2020-10-9 11:03
 */
@Repository
public interface StudentDao {

    int insertStudent(Student student);

    List<Student> selectStudents();
}
