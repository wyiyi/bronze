package com.amber.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.amber.demo.util.MergeDoc.getFilesDatas;

@SpringBootTest
class MergeDocTest {

    @Test
    void insert(){
        Map<String, String> map = getFilesDatas("F:\\南昌相关文档\\60接口");
        for(String key : map.keySet()){
            String value = map.get(key);
            System.out.println("文件名："+key+"   内容："+value);
        }
    }


}
