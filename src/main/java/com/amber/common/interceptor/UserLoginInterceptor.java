package com.amber.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(UserLoginInterceptor.class);
    private static final ThreadLocal<Long> logTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        logTimeThreadLocal.set(beginTime);
        log.info(request.getRequestURI() + " 开始执行时间：" + beginTime);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long beginTime =  logTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        log.info(request.getRequestURI() + " 执行结束时间：" + endTime + "耗时：" + (endTime - beginTime));
    }
}
