package com.example.SocialAuthLogIn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.SocialAuthLogIn.Entity")
public class SocialAuthLogInApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialAuthLogInApplication.class, args);
	}
}
