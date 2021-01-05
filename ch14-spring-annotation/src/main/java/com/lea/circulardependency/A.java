package com.lea.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lzc
 * @create 2021-1-4 16:34
 */
@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class A {

    public A() {
        System.out.println("---- A create success");
    }

    private B b;
    @Autowired
    public void setB(B b) {
        this.b = b;
    }

}
