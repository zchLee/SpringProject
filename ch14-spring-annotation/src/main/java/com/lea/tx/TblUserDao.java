package com.lea.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;
import java.util.UUID;

/**
 * @author lzc
 * @create 2020-10-26 14:57
 */
@Repository
public class TblUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "INSERT INTO tbl_user (username,age) VALUES (?, ?)";
        String username = UUID.randomUUID().toString().substring(0, 5);
        Integer age = new Random(100).nextInt();
        jdbcTemplate.update(sql, username, age);
    }
}
