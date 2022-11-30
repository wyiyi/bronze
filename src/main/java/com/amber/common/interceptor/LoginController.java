package com.amber.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(UserLoginInterceptor.class);

    @RequestMapping("/index")
    public Map<String,String> hello(Model model){
        Map<String,String> response=new HashMap<>();
        response.put("msg","hello controller !");
        log.info("hello controller !");
        return response;
    }
}
