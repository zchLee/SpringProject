package com.lea.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lzc
 * @create 2020-10-12 17:13
 */
public class UserLog {

    private static final Logger log = LoggerFactory.getLogger(UserLog.class);

    public static void main(String[] args) {
        log.info("日志打印");
    }
}
