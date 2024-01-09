package cn.amber.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan({"cn.amber.common.**.mapper"})
@Configuration
public class TestApplication {
}
