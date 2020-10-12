package com.lea.service;

/**
 * @author lzc
 * @create 2020-10-12 10:51
 */
public interface AccountService {

    // 保存账户
    void save();
    // 更新账户
    void update(int i);
    // 删除账户
    int delete(int i);
}
