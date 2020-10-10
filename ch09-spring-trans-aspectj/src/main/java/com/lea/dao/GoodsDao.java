package com.lea.dao;

import com.lea.domain.Goods;

/**
 * @author lzc
 * @create 2020-10-9 23:18
 */
public interface GoodsDao {
    // 更新库存
    int updateGoods(Goods goods);

    // 查询商品信息
    Goods selectGoods(Integer gid);
}
