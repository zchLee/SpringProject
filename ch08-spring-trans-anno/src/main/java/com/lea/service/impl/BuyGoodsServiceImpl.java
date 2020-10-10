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

//    public void setGoodsDao(GoodsDao goodsDao) {
//        this.goodsDao = goodsDao;
//    }
//
//    public void setSaleDao(SaleDao saleDao) {
//        this.saleDao = saleDao;
//    }

    /*
        属性：
            propagation:用于设置事务传播属性。该属性类型为Propagation枚举，默认类型为Propagation.REQUIRED
            isolation:用于设置事务隔离级别。该属性类型为Isolation枚举，默认类型为Isolation.DEFAULT
            readOnly:用于设置方法对数据库的操作是否是只读。该属性为boolean，默认值为false
            timeout：用于设置本操作与数据库连接的超时时限，单位为秒，类型为int，默认值为-1，没有时限
            rollbackFor:指定异常回滚的异常类，类型为Class[], 默认值为空数组。当然若只有一个异常类时，可以不使用数组
            rollbackForClassName：指定需要回滚的异常类名。类型为String[].默认也为空数组，如果只有一个异常类，也可以不用数组
            noRollbackFor：指定不需要回滚的异常类，类型为class[] 默认值为空数组。若只有一个异常类，不需要用数组
            noRollbackForClassName：指定不需要回滚的异常类名。类型为String[].默认也为空数组，如果只有一个异常类，也可以不用数组

        注意：
            @Transactional若使用在方法上，只能用在public方法上，对于非public方法,该方法不会报错也不会执行事务
            因为spring忽略所有非public方法上的@Trancational注解
         */
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = {
                    NullPointerException.class,NotEnoughException.class
            }
    )
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
