package com.yb.medical_online.fun.controller;

import com.yb.medical_online.fun.bean.test.TestBean;
import com.yb.medical_online.fun.bean.test.TestForm;
import com.yb.medical_online.fun.service.TestServiceImpl;
import com.yb.medical_online.item.bean.UserSession;
import com.yb.medical_online.item.cont.Cont;
import com.yb.medical_online.item.util.HttpResult;
import com.yb.medical_online.item.util.MyRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestServiceImpl service;


    @Resource
    private MyRedis redis;

    @ResponseBody
    @RequestMapping("/test/testList")
    public HttpResult getTestList(TestForm form) throws Exception{
        List<TestBean> list=service.getTestList(form);
        return HttpResult.success("获取数据成功",list);
    }



    @ResponseBody
    @RequestMapping("/test/redisSet")
    public HttpResult redisSet(String name) throws Exception{
        Map<String,Object> m=new HashMap<String,Object>();
        m.put("name","郭延思");
        m.put("age",25);
        redis.set("name",m);
        return HttpResult.success("redis保存成功；请使用redisGet?=查看");
    }

    @ResponseBody
    @RequestMapping("/test/redisGet")
    public HttpResult redisGet() throws Exception{
        Map object=(Map) redis.get("name");
        return HttpResult.success("请求成功",object);
    }


    @ResponseBody
    @RequestMapping("/test/redisGetAndSet")
    public HttpResult redisGetAndSet() throws Exception{
        Map<String,Object> m=new HashMap<String,Object>();
        m.put("name","郭延思123");
        m.put("age",20);
        redis.getAndSet("name",m);
        Map m2=(Map) redis.get("name");
        return HttpResult.success("redis更新缓存",m2);
    }


    @ResponseBody
    @RequestMapping("/test/redisDelete")
    public HttpResult redisDelete() throws Exception{
       boolean b= redis.delete("name");
       return HttpResult.success("redis已删除",b);
    }

    @ResponseBody
    @RequestMapping("/login")
    public HttpResult login(String code, String pass, HttpSession session) throws Exception{
        if("gys".equals(code)&&"123".equals(pass)){
            UserSession user=new UserSession();
            user.setCode(code);
            user.setName("guoyansi加个中文");
            user.setAge(20);

            session.setAttribute(Cont.userSession,user);
            return HttpResult.success("登录成功"+System.currentTimeMillis());
        }else{
            return HttpResult.error("登录失败");
        }
    }
    @ResponseBody
    @RequestMapping("/getSession")
    public HttpResult getSession(HttpSession session) throws Exception{
       UserSession user=(UserSession)session.getAttribute(Cont.userSession);
       return HttpResult.success("已获取session;id="+session.getId(),user);
    }
}
