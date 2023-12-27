package com.amber.common.handler;

import com.amber.common.annotation.FieldDesensitize;
import com.amber.common.entity.SerializeType;
import com.amber.common.service.IDesensitizer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DesensitizeHandler extends AbstractHandler<FieldDesensitize> implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private static final Map<Class<?>, Set<Field>> FIELDS__Map = new ConcurrentHashMap<>();
    private static final Set<Class<?>> CLASS_SET = new HashSet<>();

    public DesensitizeHandler() {
        super(FieldDesensitize.class);
    }


    protected Map<Class<?>, Set<Field>> getFieldsMap() {
        return FIELDS__Map;
    }

    protected Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    @Override
    String getAfterValue(FieldDesensitize annotation, String originalValue, SerializeType serializeType) {
        String fillValue = annotation.fillValue();
        IDesensitizer iDesensitizer = applicationContext.getBean(annotation.desensitizer());
        return iDesensitizer.execute(String.valueOf(originalValue), fillValue);
    }

    /**
     * 处理结果集
     *
     * @param resultList
     */
    public void handleResultList(List<Object> resultList) {
        if (resultList == null && resultList.isEmpty()) {
            return;
        }
        // 默认集合内元素一致
        parse(resultList.get(0).getClass());

        resultList.forEach(object -> {
            if (object != null) {
                handleObject(object, object.getClass(), SerializeType.EN);
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
