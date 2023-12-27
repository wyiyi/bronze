package com.amber.common;

import com.amber.common.config.PrivacyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PrivacyAutoConfiguration.class})
public class PrivacyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrivacyApplication.class, args);
    }
}
