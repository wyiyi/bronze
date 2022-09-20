package com.amber.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "corsFilter", urlPatterns = {"/*"})
@Component
public class CORSConfig implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info(" CORSFilter ��ʼ��,ֻ��ʼ��һ��...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info(" CORSFilter ִ��...");
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Credentials", String.valueOf(true));
        res.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info(" CORSFilter Filter����...");
    }
}
