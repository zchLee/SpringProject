package org.example.aop02;

/**
 * @author lzc
 * @create 2020-10-5 12:24
 */
public interface SomeService {

    void doSome(String name, Integer age);

    String doOther(String name, Integer age);

    Student doStu(String name, Integer age);
}
