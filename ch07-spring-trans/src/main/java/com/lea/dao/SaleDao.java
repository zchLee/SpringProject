package com.lea.dao;

import com.lea.domain.Sale;


/**
 * @author lzc
 * @create 2020-10-9 23:00
 */
public interface SaleDao {
    // 增加销售记录
    int insertSale(Sale sale);
}
