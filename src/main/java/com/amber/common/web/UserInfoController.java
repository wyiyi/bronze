package com.amber.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/amber/userinfo")
public class UserInfoController {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
//    @CrossOrigin
    public String getUser(){
        return "hello, amber!";
    }
}
