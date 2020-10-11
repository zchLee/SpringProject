package com.lea.factory;

import com.lea.service.SomeService;
import com.lea.service.impl.SomeServiceImpl;

/**
 * @author lzc
 * @create 2020-10-11 10:29
 */
public class InstanceFactory {

    public SomeService getSomeService() {
        return new SomeServiceImpl();
    }
}
