package com.amber.common.sensitive;

import com.amber.common.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 基于 MyBatis 的拦截器，针对敏感数据，从 `写入数据库` 和 `数据库中读取` 两个环节对敏感数据进行处理
 * 自定义拦主要截器拦截 update 和 insert
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Component
public class DataSensitiveInterceptor implements Interceptor {

    private final Map<String, String> configs;

    /**
     * 获取配置文件中需要加密的属性
     * @param config DataSensitiveConfig
     */
    public DataSensitiveInterceptor(DataSensitiveConfig config) {
        this.configs = config.getSensitive();
    }

    /**
     * 通过拦截器处理
     * @param invocation invocation
     * @return Object
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof Executor) {
            Object object = invocation.getArgs()[1];
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (!entry.getKey().startsWith("param")) {
                        handleEncrypt(entry.getValue());
                    }
                    continue;
                }
            } else {
                handleEncrypt(object);
            }
            return invocation.proceed();
        } else if (invocation.getTarget() instanceof ResultSetHandler) {
            ResultSetHandler resultSetHandler = (ResultSetHandler) invocation.getTarget();
            Statement statement = (Statement) invocation.getArgs()[0];
            List<Object> resultList = resultSetHandler.handleResultSets(statement);
            resultList.forEach(this::handleDecrypt);
            return resultList;
        }
        return invocation.proceed();
    }

    private void handleEncrypt(Object object) {
        handleObject(object, true);
    }

    private void handleDecrypt(Object object) {
        handleObject(object, false);
    }

    private void handleObject(Object object, boolean encrypt) {
        for (Map.Entry<String, String> config : configs.entrySet()) {
            int lastPoint = config.getKey().lastIndexOf('.');
            String className = config.getKey().substring(0, lastPoint);
            if (object.getClass().getName().equals(className)) {
                String property = config.getKey().substring(lastPoint + 1);
                String handlerName = config.getValue();
                DataSensitiveHandler handler = SpringContextHolder.getBean("dataSensitiveHandler-" + handlerName);
                BeanWrapper wrapper = new BeanWrapperImpl(object);
                wrapper.setPropertyValue(property,
                        encrypt ? handler.encrypt(String.valueOf(wrapper.getPropertyValue(property))) :
                                handler.decrypt(String.valueOf(wrapper.getPropertyValue(property)))
                );
            }
        }
    }
}
