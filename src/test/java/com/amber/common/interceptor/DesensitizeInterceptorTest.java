package com.amber.common.interceptor;

import com.amber.common.model.User1Dto;
import com.amber.common.model.UserDto;
import com.amber.common.PrivacyApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PrivacyApplication.class})
public class DesensitizeInterceptorTest {

    @Autowired
    private IInnerInterceptor desensitizeInterceptor;

    /**
     * 查询脱敏参数
     */
    @Test
    public void selectTest1() {
        List<Object> paramList = getParamList();
        desensitizeInterceptor.afterQuery(paramList);
        Assert.assertEquals(getAssertParamList(), paramList);
    }
    /**
     * 查询脱敏参数-类包含自己类
     */
    @Test
    public void selectTest2() {
        List<Object> paramList = getParamList2();
        desensitizeInterceptor.afterQuery(paramList);
        Assert.assertEquals(getAssertParamList2(), paramList);
    }

    private List<Object> getParamList() {
        List<Object> paramList = new ArrayList<>();
        Collections.addAll(paramList,
                new UserDto("zhangsan", "123456", "19969999999", 1),
                new UserDto("lisi", "654321", "16969999666", 1)
        );
        return paramList;
    }
    private List<Object> getParamList2() {
        List<Object> paramList = new ArrayList<>();
        Collections.addAll(paramList,
                new User1Dto("zhangsan", "123456", "19969999999", 1,new User1Dto("zhangsan2", "123456", "19969999999", 1,null)),
                new User1Dto("lisi", "654321", "16969999666", 1,null)
        );
        return paramList;
    }

    private List<Object> getAssertParamList() {
        List<Object> paramList = new ArrayList<>();
        Collections.addAll(paramList,
                new UserDto("zhangsan", "123456", "199****9999", 1),
                new UserDto("lisi", "654321", "169****9666", 1)
        );
        return paramList;
    }
    private List<Object> getAssertParamList2() {
        List<Object> paramList = new ArrayList<>();
        Collections.addAll(paramList,
                new User1Dto("zhangsan", "123456", "199****9999", 1,new User1Dto("zhangsan2", "123456", "199****9999",1,null)),
                new User1Dto("lisi", "654321", "169****9666", 1,null)
        );
        return paramList;
    }

}
