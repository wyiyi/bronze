package com.amber.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/amber/userinfo")
//@CrossOrigin
public class UserInfo {

    @GetMapping("/hello")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public String getUser(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "hello, amber!";
    }
}
