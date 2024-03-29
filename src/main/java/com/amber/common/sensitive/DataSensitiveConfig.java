package com.amber.common.sensitive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取 com.amber.common 配置的实体类中的敏感字段
 */
@Component
@ConfigurationProperties("com.amber.common")
public class DataSensitiveConfig {

    private Map<String, String> sensitive = new HashMap<>();

    /**
     * 获取敏感数据方法
     * @return sensitive
     */
    public Map<String, String> getSensitive() {
        return sensitive;
    }
}
