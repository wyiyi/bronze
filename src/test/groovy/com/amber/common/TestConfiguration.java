package com.amber.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.amber.common.**.mapper"})
@SpringBootApplication
public class TestConfiguration {
}
