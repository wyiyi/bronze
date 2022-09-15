package com.amber.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@MapperScan({"com.amber.common.dao"})
public class BronzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BronzeApplication.class, args);
	}

}
