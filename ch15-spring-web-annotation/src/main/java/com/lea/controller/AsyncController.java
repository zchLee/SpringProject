package com.lea.controller;

import com.lea.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

/**
 * @author lzc
 * @create 2020-10-29 18:04
 */
@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> result = new DeferredResult<>(6000L, "create fail...");
        DeferredResultQueue.save(result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        // 创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> result = DeferredResultQueue.get();
        result.setResult(order);
        return "success=======>" + order;
    }
}
