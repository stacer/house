package com.mooc.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.mooc.house.autoconfig.EnableHttpClient;

@SpringBootApplication
@EnableHttpClient	//步骤：第三章1.10.2
@EnableAsync //步骤：第四章1.3
public class HouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseApplication.class, args);
	}
}
