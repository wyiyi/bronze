package com.amber.demo.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping("/hello")
    public Map<String,String> hello(Model model){
        Map<String,String> response=new HashMap<>();
        response.put("msg","hello");
        return response;
    }
}
