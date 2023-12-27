package com.amber.common.config;

import com.amber.common.handler.CryptHandler;
import com.amber.common.handler.DesensitizeHandler;
import com.amber.common.interceptor.CryptoInterceptor;
import com.amber.common.interceptor.DesensitizeInterceptor;
import com.amber.common.interceptor.PrivacyInterceptor;
import com.amber.common.properties.CryptoProperties;
import com.amber.common.service.impl.DefaultCrypto;
import com.amber.common.service.impl.DefaultDesensitizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CryptoProperties.class)
public class PrivacyAutoConfiguration {

    @Bean
    public PrivacyInterceptor privacyInterceptor() {
        return new PrivacyInterceptor();
    }

    @Bean
    public DesensitizeHandler desensitizeHandler() {
        return new DesensitizeHandler();
    }

    @Bean
    public CryptHandler cryptHandler(CryptoProperties cryptoProperties) {
        return new CryptHandler(cryptoProperties);
    }

    @Bean
    public CryptoInterceptor cryptoInterceptor(CryptHandler cryptHandler) {
        return new CryptoInterceptor(cryptHandler);
    }

    @Bean
    public DesensitizeInterceptor desensitizeInterceptor(DesensitizeHandler desensitizeHandler) {
        return new DesensitizeInterceptor(desensitizeHandler);
    }

    @Bean
    public DefaultCrypto defaultCrypto() {
        return new DefaultCrypto();
    }

    @Bean
    public DefaultDesensitizer defaultDesensitizer() {
        return new DefaultDesensitizer();
    }


}
