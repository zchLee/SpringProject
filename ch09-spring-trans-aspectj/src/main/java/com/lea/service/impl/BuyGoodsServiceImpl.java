package com.lea.service.impl;

import com.lea.dao.GoodsDao;
import com.lea.dao.SaleDao;
import com.lea.domain.Goods;
import com.lea.domain.Sale;
import com.lea.exception.NotEnoughException;
import com.lea.service.BuyGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lzc
 * @create 2020-10-9 23:30
 */
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SaleDao saleDao;

    @Override
    public void buy(Integer goodsId, Integer nums) {
        System.out.println("buy方法的开始--------------");
        // 记录销售信息，向sale表添加销售信息
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(nums);
        saleDao.insertSale(sale);
        // 更新库存
        Goods goods = goodsDao.selectGoods(goodsId);
        if (null == goods) {
            throw new NullPointerException("商品id不存在");
        }
        if (goods.getAmount() < nums) {
            throw new NotEnoughException("商品库存不足");
        }
        Goods params = new Goods();
        params.setId(goodsId);
        params.setAmount(nums);
        goodsDao.updateGoods(params);
        System.out.println("buy方法的完成--------------");
    }
}
