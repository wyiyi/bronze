package com.amber.common.filter;


import com.amber.common.entity.User;
import com.amber.common.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Order(1)
@WebFilter(filterName = "aFilter", value = "/*")
//@Component
public class AFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AFilter.class);
    @Autowired
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("A Filter初始化,只初始化一次...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("AFilter 处理中...");
        User user = userService.getUserInfo();
        LOGGER.info("USER 信息：" +  user);
    }

    @Override
    public void destroy() {
        LOGGER.info("A Filter销毁...");
    }
}
