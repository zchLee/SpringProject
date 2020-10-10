package com.lea.exception;

/**
 * @author lzc
 * @create 2020-10-9 23:34
 *
 * 自定义运行时异常
 */
public class NotEnoughException extends RuntimeException {

    public NotEnoughException() {super();}

    public NotEnoughException(String message) {
        super(message);
    }

}
