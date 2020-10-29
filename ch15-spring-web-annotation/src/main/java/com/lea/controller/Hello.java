package com.lea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @author lzc
 * @create 2020-10-29 15:37
 */
@Controller
public class Hello {

    @ResponseBody
    @RequestMapping("hello")
    public String hi() {
        return "success";
    }

    @RequestMapping("success")
    public String suc(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }

    @ResponseBody
    @RequestMapping("suc")
    public String success(HttpServletRequest request, HttpServletResponse response) {
        // servlet3.0 处理异步
        System.out.println(Thread.currentThread() + "时间：" + System.currentTimeMillis());
        AsyncContext async = request.startAsync(request, response);
        async.start(() -> {
            System.out.println(Thread.currentThread() + "副线程时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "副线程时间：" + System.currentTimeMillis());
        });
        System.out.println(Thread.currentThread() + "线程时间：" + System.currentTimeMillis());
        return "success";
    }

    /*
    spring mvc 异步  Callable<?>
    1、控制器返回 Callable 后， spring mvc
    2、Spring MVC异步处理，将Callable提交到 TaskExecutor 使用一个隔离的线程 执行
    3、DispatcherServlet和所有的 Filter退出wen容器的线程，但是response保持打开状态
    4、Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理
    5、根据Callable返回的结果，SpringMVC进行处理
     */
    @ResponseBody
    @RequestMapping("async01")
    public Callable<String> async01() {
        System.out.println("主线程：" + Thread.currentThread() + ":" + System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程：" + Thread.currentThread() + ":" + System.currentTimeMillis());
            Thread.sleep(6000);
            System.out.println("副线程：" + Thread.currentThread() + ":" + System.currentTimeMillis());
            return "async01()";
        };
        System.out.println("主线程：" + Thread.currentThread() + ":" + System.currentTimeMillis());
        return callable;
    }
}
