package com.amber.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amber.common.entity.MybatisDemo;
import com.amber.common.entity.User;
import com.amber.common.service.MybatisService;
import com.amber.common.service.UserService;
import com.amber.common.util.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MybatisService mybatisService;

    @Override
    public List<User> getInfo() {
        try {
            List<User> weiBos = new ArrayList<>();
            String outPut = HttpClient.sendGet("https://m.weibo.cn/comments/hotflow?id=4160547165300149&mid=4160547165300149&max_id_type=0");
            JSONArray jsonArray = (JSONArray) ((JSONObject) JSONObject.parseObject(outPut).get("data")).get("data");
            for (Object value : jsonArray) {
                User weiBo = new User();
                weiBo.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(String.valueOf(((JSONObject) value).get("created_at")))));
                weiBo.setText(String.valueOf(((JSONObject) value).get("text")));
                weiBo.setScreen_name(String.valueOf(((JSONObject) ((JSONObject) value).get("user")).get("screen_name")));
                weiBos.add(weiBo);
            }
            printToTXT(weiBos);
            return weiBos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserInfo() {
        User user = new User();
        user.setScreen_name("amber");
        user.setCreated_at(String.valueOf(new Date()));
        user.setText("今天星期四");
        return user;
    }

    private void printToTXT(List<User> weiBos) throws FileNotFoundException {
        PrintStream ps = new PrintStream("C:\\Users\\Administrator\\Desktop\\sina.txt");
        System.setOut(ps);
        System.out.println(weiBos);
        ps.close();
    }

    private void updateBatch(List<MybatisDemo> mybatisDemos){
        mybatisService.saveOrUpdateBatch(mybatisDemos);
        mybatisService.updateBatchById(mybatisDemos);
    }
}