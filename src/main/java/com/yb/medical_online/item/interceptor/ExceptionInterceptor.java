package com.yb.medical_online.item.interceptor;

import com.yb.medical_online.item.ex.*;
import com.yb.medical_online.item.util.HttpResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionInterceptor {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public HttpResult exceptionHandler(Exception e){
        e.printStackTrace();
        return HttpResult.errorEx();
    }
    @ResponseBody
    @ExceptionHandler({MyException.class})
    public HttpResult myExceptionHandler(MyException e){
        return HttpResult.error(e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler({MyNoSessionException.class})
    public HttpResult myNoSessionExceptionHandler(MyNoSessionException e){
        return HttpResult.noSessionEx();
    }

    @ExceptionHandler({MyViewException.class})
    public ModelAndView myViewException(MyViewException e){
        ModelAndView view=new ModelAndView();
        view.addObject("msg",e.getMessage());
        if(e.getHref()==null){
            view.setViewName("expage");
        }else{
            view.setViewName(e.getHref());
        }

        return view;
    }
    @ExceptionHandler({MyViewNoSessionException.class})
    public ModelAndView myViewNoSessionException(MyNoSessionException e){
        ModelAndView view=new ModelAndView();
        view.addObject("msg",e.getMessage());
        view.setViewName("expage");
        return view;
    }
}
