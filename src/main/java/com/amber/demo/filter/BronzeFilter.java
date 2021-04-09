package com.amber.demo.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class BronzeFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BronzeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("Filter初始化,只初始化一次...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        LOGGER.info("拦截前执行...");
        chain.doFilter(request,response);
        LOGGER.info("拦截后执行...");
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter销毁...");
    }
}
