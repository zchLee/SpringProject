package com.lea.service.impl;

import com.lea.service.AccountService;

/**
 * @author lzc
 * @create 2020-10-12 10:52
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void save() {
        System.out.println("保存账户");
//        int i = 10 / 0;
    }

    @Override
    public void update(int i) {
        System.out.println("修改账户");
    }

    @Override
    public int delete(int i) {
        System.out.println("删除账户");
        return 0;
    }
}
