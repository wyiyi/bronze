package com.amber.common.web;

import com.amber.common.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/amber/userinfo")
public class UserInfoController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
//    @CrossOrigin
    public String getUser(){
        return "hello, amber!";
    }

}
