package com.yb.medical_online.item.ex;

/**
 * 刷新页面时的自定义抛出异常
 */
public class MyViewException extends RuntimeException{

    private String href;
    public MyViewException() {
    }

    public MyViewException(String message) {
        super(message);
    }
    public MyViewException(String message,String href) {
        super(message);
        this.href=href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
