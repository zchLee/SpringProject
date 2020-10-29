package com.lea.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lzc
 * @create 2020-10-29 18:07
 */
public class DeferredResultQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<>();

    public static void save(DeferredResult<Object> deferredResult) {
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get() {
        return queue.poll();
    }

}
