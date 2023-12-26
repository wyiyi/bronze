package com.amber.common.filter;

import com.amber.common.enums.SensitiveTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SensitiveFilter{

    public void getTableColumn(){

    }

    public Map<String, String> getFieldAnnotation(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, String> resultMap = new HashMap();
        for (Field field : fields) {
            // 是否引用ApiModelProperty注解
            boolean bool = field.isAnnotationPresent(ApiModelProperty.class);
            if (bool) {
                String value = field.getAnnotation(ApiModelProperty.class).value();
                resultMap.put(field.getName(), value);
            }
        }
        return resultMap;
    }

}
