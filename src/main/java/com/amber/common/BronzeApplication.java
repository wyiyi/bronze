package com.amber.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.amber.common.mapper"})
public class BronzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BronzeApplication.class, args);
	}

}
