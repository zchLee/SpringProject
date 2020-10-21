package com.lea.conf;

import com.lea.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lzc
 * @create 2020-10-21 15:59
 */
//@Configuration
//@PropertySource(value = "classpath:person.properties")
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
