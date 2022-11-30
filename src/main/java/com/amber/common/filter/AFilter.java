package com.amber.common.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import java.io.IOException;

//@Order(1)
//@WebFilter(filterName = "aFilter", value = "/*")
//@Component
public class AFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("A Filter初始化,只初始化一次...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info(" AFilter 处理中...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        LOGGER.info("A Filter销毁...");
    }
}
