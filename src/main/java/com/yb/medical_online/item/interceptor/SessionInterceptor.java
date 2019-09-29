package com.yb.medical_online.item.interceptor;

import com.alibaba.fastjson.JSON;
import com.yb.medical_online.item.bean.UserSession;
import com.yb.medical_online.item.cont.Cont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter
public class SessionInterceptor implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        System.out.println("请求url:"+uri);
        //允许通过的关键字
        String[] allowKey={"/loginpage","/test","/login"};
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri,allowKey);

        UserSession user=(UserSession) request.getSession().getAttribute(Cont.userSession);
        if (needFilter||user!=null) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
                String requestType = request.getHeader("X-Requested-With");
                if(requestType==null){//普通请求
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print("<script language='javascript'>");
                    response.getWriter().print("window.location.href='" + request.getContextPath() + "/loginPage';");
                    response.getWriter().print("</script>");
                }else{//ajax请求
                    response.setContentType("text/plain;charset=utf-8");
                    Map<String,Object> map=new HashMap<String, Object>();
                    map.put(Cont.status,Cont.ns);
                    map.put(Cont.msg,Cont.noSession);
                    response.getWriter().print(JSON.toJSONString(map));
                }


        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 是否需要过滤
     * @param uri
     * @return
     */
    public boolean isNeedFilter(String uri,String[] arr) {
        for(String s:arr){
            if(uri.indexOf(s)>-1){
                return true;
            }
        }
        return false;
    }


}
