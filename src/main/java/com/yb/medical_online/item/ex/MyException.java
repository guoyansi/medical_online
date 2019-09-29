package com.yb.medical_online.item.ex;


/**
 * ajax请求出现的抛出自定义异常
 */
public class MyException extends RuntimeException{

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
