package com.amber.common.web;

import com.amber.common.entity.WeiBo;
import com.amber.common.service.WeiBoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "/api/amber/weibo", tags = {"获取微博评论信息"})
@RequestMapping("/api/amber/weibo")
public class WeiBoController {

    @Autowired
    private WeiBoService weiBoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WeiBo> getWeiBo() {
        return weiBoService.getInfo();
    }
}
