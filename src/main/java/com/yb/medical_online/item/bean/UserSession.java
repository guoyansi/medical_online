package com.yb.medical_online.item.bean;

import java.io.Serializable;

public class UserSession implements Serializable{

    private String code;
    private String name;
    private Integer age;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
