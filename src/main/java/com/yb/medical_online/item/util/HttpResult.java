package com.yb.medical_online.item.util;

public class HttpResult {
    private Integer status;
	private Integer code;
	private String msg;
    private Object res;

    public HttpResult() {
		super();
	}
    public HttpResult(Integer status) {
		super();
		this.status = status;
	}
	public HttpResult(Integer status, Integer code) {
		super();
		this.status = status;
		this.code=code;
	}
    
    public HttpResult(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public HttpResult(Integer status, Integer code, String msg) {
		super();
		this.code=code;
		this.status = status;
		this.msg = msg;
	}
    public HttpResult(Integer status, String msg, Object res) {
		super();
		this.status = status;
		this.msg = msg;
		this.res = res;
	}
	public HttpResult(Integer status, Integer code, String msg, Object res) {
		super();
		this.status = status;
		this.code=code;
		this.msg = msg;
		this.res = res;
	}

	public  static HttpResult success(){
        return new HttpResult(1);
    }
	public  static HttpResult success(Integer code){
		return new HttpResult(1,code);
	}

    public  static HttpResult success(String msg){
        return new HttpResult(1,msg);
    }
    public  static HttpResult success(String msg, Object res){
    	return new HttpResult(1,msg,res);
    }
	public  static HttpResult success(Integer code,String msg, Object res){
		return new HttpResult(1,code,msg,res);
	}
	public  static HttpResult success(Integer code, Object res){
		return new HttpResult(1,code,null,res);
	}
    public  static HttpResult error(){
    	return new HttpResult(2);
    }

    public  static HttpResult error(String msg){
        return new HttpResult(2,msg);
    }
	public  static HttpResult error(Integer code,String msg){
		return new HttpResult(2,code,msg);
	}
    public  static HttpResult error(String msg, Object res){
    	return new HttpResult(2,msg,res);
    }
	public  static HttpResult error(Integer code,String msg, Object res){
		return new HttpResult(2,code,msg,res);
	}
	public  static HttpResult errorEx(){
		return new HttpResult(2,"程序异常");
	}
	public  static HttpResult noSessionEx(){
		return new HttpResult(3,"session失效");
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRes() {
		return res;
	}

	public void setRes(Object res) {
		this.res = res;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
