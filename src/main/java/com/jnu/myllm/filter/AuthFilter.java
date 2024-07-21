package com.jnu.myllm.filter;

import com.alibaba.fastjson.JSON;
import com.jnu.myllm.common.ServerResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
@Slf4j
public class AuthFilter implements javax.servlet.Filter {
    //这个session过滤器会在跨域检验之前执行，因此，对于某些具有预检请求的跨域请求，其预检请求首先会被该过滤器校验，放行后才会进入跨域校验
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        return ;
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        if(req.getMethod().equals("OPTIONS")){
//            log.info("预检请求，直接放行");
//            chain.doFilter(req, res);
//            log.info("本次session过滤器执行结束---------------------------------------");
//            return;
//        }
//
//        String url = req.getRequestURI();
//        log.info("本次处理的请求为:{}", url);
//        if (url.contains("login") || url.contains("register") ) {
//            log.info("登录请求已经放行,本次会话ID为:{}",req.getRequestedSessionId());
//            chain.doFilter(req, res);
//            log.info("本次session过滤器执行结束---------------------------------------");
//            return;
//        }
//
//        if(req.getHeader("Authorization")!=null){
//            log.info("携带token，跳过cookies验证");
//            chain.doFilter(req, res);
//            log.info("本次session过滤器执行结束---------------------------------------");
//            return;
//        }
//
//        if(req.getSession().getAttribute("user")!=null){
//            log.info("已放行，{}",req.getSession().getAttribute("user"));
//            log.info("本次会话ID为:{}",req.getRequestedSessionId());
//            chain.doFilter(req, res);
//            log.info("本次session过滤器执行结束---------------------------------------");
//            return;
//        }
//
//        log.info("未登录，已拦截");
//        log.info("本次session过滤器执行结束---------------------------------------");
//        ServerResponseEntity serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setCode(401);
//        serverResponseEntity.setMsg("NOLOGIN");
//        res.getWriter().write(JSON.toJSONString(serverResponseEntity));

    }
}
