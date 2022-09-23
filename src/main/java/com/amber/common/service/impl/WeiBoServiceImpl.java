package com.amber.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amber.common.entity.WeiBo;
import com.amber.common.service.WeiBoService;
import com.amber.common.util.HttpClient;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeiBoServiceImpl implements WeiBoService {

    @Override
    public List<WeiBo> getInfo() {
        try {
            List<WeiBo> weiBos = new ArrayList<>();
            String outPut = HttpClient.sendGet("https://m.weibo.cn/comments/hotflow?id=4160547165300149&mid=4160547165300149&max_id_type=0");
            JSONArray jsonArray = (JSONArray) ((JSONObject) JSONObject.parseObject(outPut).get("data")).get("data");
            for (Object value : jsonArray) {
                WeiBo weiBo = new WeiBo();
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

    private void printToTXT(List<WeiBo> weiBos) throws FileNotFoundException {
        PrintStream ps = new PrintStream("C:\\Users\\Administrator\\Desktop\\sina.txt");
        System.setOut(ps);
        System.out.println(weiBos);
        ps.close();
    }
}
