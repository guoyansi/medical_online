package com.yb.medical_online;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.yb")
public class MedicalOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalOnlineApplication.class, args);
	}

}
