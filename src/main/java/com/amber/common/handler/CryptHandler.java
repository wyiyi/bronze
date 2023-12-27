package com.amber.common.handler;

import com.amber.common.annotation.FieldEncrypt;
import com.amber.common.entity.Algorithm;
import com.amber.common.entity.SerializeType;
import com.amber.common.properties.CryptoProperties;
import com.amber.common.service.ICrypto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class CryptHandler extends AbstractHandler<FieldEncrypt> implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private static final Map<Class<?>, Set<Field>> FIELDS__Map = new ConcurrentHashMap<>();
    private static final Set<Class<?>> CLASS_SET = new CopyOnWriteArraySet<>();
    private final CryptoProperties cryptoProperties;

    public CryptHandler(CryptoProperties cryptoProperties) {
        super(FieldEncrypt.class);
        this.cryptoProperties = cryptoProperties;
    }

    protected Map<Class<?>, Set<Field>> getFieldsMap() {
        return FIELDS__Map;
    }

    protected Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    @Override
    String getAfterValue(FieldEncrypt annotation, String originalValue, SerializeType serializeType) {

        String key;
        //全局配置的key
        String propertiesKey = cryptoProperties.getKey();
        log.debug("全局key是：" + propertiesKey);
        //属性上的key
        String annotationKey = annotation.key();
        log.debug("注解key是：" + annotationKey);

        if (!"".equals(annotationKey)) {
            key = annotationKey;
        } else {
            key = propertiesKey;
        }

        Algorithm algorithm = annotation.algorithm();

        ICrypto iCrypto = applicationContext.getBean(annotation.iCrypto());

        String valueResult;
        try {
            if (serializeType.equals(SerializeType.DE)) {
                valueResult = iCrypto.decrypt(algorithm, String.valueOf(originalValue), key);
            } else {
                valueResult = iCrypto.encrypt(algorithm, String.valueOf(originalValue), key);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return valueResult;
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
                handleObject(object, object.getClass(), SerializeType.DE);
            }
        });
    }

    /**
     * 处理参数
     *
     * @param parameter
     */
    public void handleParam(Object parameter) {

        if (parameter == null) {
            return;
        }

        if (parameter instanceof Map) {
            Map paramMap = (Map) parameter;
            Set<Object> handledObjectList = new HashSet<>();
            for (Object value : paramMap.values()) {
                if (value != null) {
                    if (value instanceof Collection) {
                        for (Object item : ((Collection<?>) value)) {
                            if (handledObjectList.contains(item)) {
                                continue;
                            }
                            parse(item.getClass());
                            handleObject(item, item.getClass(), SerializeType.EN);
                            handledObjectList.add(item);
                        }
                    } else {
                        parse(value.getClass());
                        handleObject(value, value.getClass(), SerializeType.EN);
                    }
                }
            }

        } else {
            parse(parameter.getClass());
            handleObject(parameter, parameter.getClass(), SerializeType.EN);
        }

    }

    /**
     * 处理参数或结果
     *
     * @param object
     * @param serializeType
     */
    protected void handleParameterOrResult(Object object, SerializeType serializeType) {
        //多个参数
        if (object instanceof Map) {
            Map paramMap = (Map) object;
            Set keySet = paramMap.keySet();
            for (Object key : keySet) {
                Object o = paramMap.get(key);
                // 如果参数是集合类型，根据遍历处理
                if (o != null) {
                    if (o instanceof Collection) {
                        for (Object item : ((Collection<?>) o)) {
                            parse(item.getClass());
                            handleObject(item, item.getClass(), serializeType);
                        }
                    } else {
                        //
                        parse(o.getClass());
                        handleObject(o, o.getClass(), serializeType);
                    }
                }
            }
        } else {
            if (object != null) {
                //
                parse(object.getClass());
                handleObject(object, object.getClass(), serializeType);
            }
        }

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
