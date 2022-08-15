package com.amber.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/amber/userinfo")
public class UserInfo {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(){
        return "hello, amber!";
    }
}
