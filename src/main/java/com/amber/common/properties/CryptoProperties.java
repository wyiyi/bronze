package com.amber.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "privacy.crypto")
public class CryptoProperties {
    /**
     * 秘钥
     */
    private String key;

}
