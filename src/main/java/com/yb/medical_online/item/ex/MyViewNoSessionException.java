package com.yb.medical_online.item.ex;


/**
 * 刷新页面时的session失效异常
 */
public class MyViewNoSessionException extends RuntimeException{

    public MyViewNoSessionException() {
        super();
    }

    public MyViewNoSessionException(String message) {
        super(message);
    }
}
