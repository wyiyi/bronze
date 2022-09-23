package com.amber.common.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import java.io.IOException;

//@Order(0)
//@WebFilter(filterName = "bFilter", value = "/*")
//@Component
public class BFilter implements Filter{
    private static final Logger LOGGER = LoggerFactory.getLogger(BFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("B Filter初始化,只初始化一次...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("B 拦截前执行...");
        chain.doFilter(request,response);
        LOGGER.info("B 拦截后执行...");
    }

    @Override
    public void destroy() {
        LOGGER.info("B Filter销毁...");
    }

}
