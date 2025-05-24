package com.example.socialCreateAccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SocialCreateAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialCreateAccountServiceApplication.class, args);
	}

}
