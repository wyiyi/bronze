package com.amber.common.web;

import com.amber.common.entity.Columns;
import com.amber.common.entity.User;
import com.amber.common.enums.SensitiveTypeEnum;
import com.amber.common.filter.SensitiveFilter;
import com.amber.common.service.ColumnsService;
import com.amber.common.service.impl.ColumnsServiceImpl;
import com.amber.common.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Map;

@SpringBootTest
public class ColumTest {
    private SensitiveFilter sensitiveFilter = new SensitiveFilter();
    @Autowired
    private ColumnsServiceImpl columnsService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void getEntityColumn() {
        User user = new User();
        user.setUsername("王开心");
        user.setPhone("13112312253");
        user.setId_card("210726200001093226");
        user.setAddress("沈阳市浑南区");
        user.setEmail("w_yi.neu@neusoft.com");
        user.setSex("1");
        user.setCreate_date(new Date());
        user.setBirth(new Date());
        userService.add(user);

        Map<String, String> map = sensitiveFilter.getFieldAnnotation(new User());
        System.out.println(map);
        // 先登记User表中所有字段
        for(Map.Entry<String, String> vo : map.entrySet()){
            System.out.println(vo.getKey()+"  "+vo.getValue());
            Columns columns = new Columns();
            columns.setField_id(vo.getKey());
            columns.setField_name(vo.getValue());
            columns.setPermission("yes");
            columns.setPath("/user/*");
            columns.setResource_name("用户信息");
            columnsService.add(columns);
        }
    }

}
