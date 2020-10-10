package com.lea;

import com.lea.service.impl.BuyGoodsServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzc
 * @create 2020-10-9 23:44
 */
public class MyTest {

    @Test
    public void test() {
        String config = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        BuyGoodsServiceImpl buyGoodsService = context.getBean("buyGoodsService", BuyGoodsServiceImpl.class);
        buyGoodsService.buy(1001, 200);
    }
}
