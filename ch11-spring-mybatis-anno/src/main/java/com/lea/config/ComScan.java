package com.lea.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author lzc
 * @create 2020-10-11 17:15
 */
@ComponentScan("com.lea")
@Import(SpringConfiguration.class)
public class ComScan {

}
