package com.amber.common.filter;

import com.amber.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ComponentFilterOrderConfig {
//    @Autowired
//    private AFilter aFilter;
//    @Bean
//    public FilterRegistrationBean filterBeanOne(AFilter aFilter) {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(aFilter);
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setName("aFilter");
//        return filterRegistrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean filterBeanOne() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new AFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setOrder(3);
//        return filterRegistrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean filterBeanTwo() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new BFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setOrder(2);
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterBeanThree() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new CFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setOrder(1);
//        return filterRegistrationBean;
//    }
}
