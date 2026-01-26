package com.ecommerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class CustomerModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerModuleApplication.class, args);
	}

}
