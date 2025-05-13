package com.example.savePost2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SavePost2Application {

	public static void main(String[] args) {
		SpringApplication.run(SavePost2Application.class, args);
	}

}
