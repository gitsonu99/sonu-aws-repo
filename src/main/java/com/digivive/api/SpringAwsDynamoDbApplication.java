package com.digivive.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.digivive.api"})
public class SpringAwsDynamoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsDynamoDbApplication.class, args);
	}

}
