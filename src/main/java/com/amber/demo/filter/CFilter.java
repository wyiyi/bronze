package com.amber.demo.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(-1)
@WebFilter(filterName = "cFilter", value = "/*")
@Component
public class CFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("C Filter初始化,只初始化一次...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("C 拦截前执行...");
        chain.doFilter(request,response);
        LOGGER.info("C 拦截后执行...");
    }

    @Override
    public void destroy() {
        LOGGER.info("C Filter销毁...");
    }
}
