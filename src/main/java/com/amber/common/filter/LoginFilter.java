package com.amber.common.filter;

import com.alibaba.fastjson.JSONObject;
import com.amber.common.entity.User;
import com.amber.common.interceptor.UserLoginInterceptor;
import com.amber.common.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebFilter(filterName = "loginFilter", value = "/*")
//@Component
//@Configuration
public class LoginFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(UserLoginInterceptor.class);
    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        ServletContext servletContext = filterConfig.getServletContext();
//        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        userService = (UserService) applicationContext.getBean(UserService.class);
//        userService.getUserInfo();
        log.info("init 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = userService.getUserInfo();
        log.info("222222222" +  user);
    }

    @Override
    public void destroy() {

    }
}
