package com.yb.medical_online.item.ex;

/**
 * ajax请求出现session失效
 */
public class MyNoSessionException extends RuntimeException{

    public MyNoSessionException() {
        super();
    }

    public MyNoSessionException(String message) {
        super(message);
    }
}
