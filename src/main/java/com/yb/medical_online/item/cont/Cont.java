package com.yb.medical_online.item.cont;

public class Cont {

    /**
     * 成功状态
     */
    public static int s=1;

    /**
     * 失败状态
     */
    public static int e=2;
    /**
     * 没有session
     */
    public static  int ns=3;
    /**
     * 程序异常
     */
    public static int ex=4;

    /**
     * 程序异常
     */
    public static String exMsg="程序异常";

    public static String status="status";

    public static String msg="msg";


    /**
     * session失效
     */
    public static final String noSession="登录信息失效，请重新登录";

    /**
     * session键值
     */
    public  static final String userSession="userSession";
}
