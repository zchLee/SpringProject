package com.lea;


import com.lea.tx.TblUserService;
import com.lea.tx.TxConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lzc
 * @create 2020-10-22 14:05
 */
public class TestTX {

    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
        TblUserService service = ac.getBean(TblUserService.class);
        service.insert();
    }
}
