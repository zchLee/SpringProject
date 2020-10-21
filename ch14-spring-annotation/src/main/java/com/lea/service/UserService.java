package com.lea.service;

import com.lea.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lzc
 * @create 2020-10-20 17:49
 */
@Service
public class UserService {

//    @Autowired
//    @Qualifier("userDao2")
    @Resource
    private UserDao userDao;

    public void dao() {
        System.out.println(userDao.getClass());
    }
}
