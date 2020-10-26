package com.lea.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lzc
 * @create 2020-10-26 14:58
 */
@Service
public class TblUserService {

    @Autowired
    private TblUserDao userDao;

    @Transactional
    public void insert() {
        userDao.insert();
        System.out.println("插入成功，service");
        int i = 1 / 0;
    }
}
