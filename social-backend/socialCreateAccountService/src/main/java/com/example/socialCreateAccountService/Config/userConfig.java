package com.example.socialCreateAccountService.Config;

import com.example.socialCreateAccountService.Services.CreateAccount;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories(basePackages = "com.example.socialCreateAccountService.Repo")
public class userConfig {
    @Bean
    public CreateAccount createAccount(){
        return new CreateAccount();
    }
    @Bean
    public ModelMapper modelMapperBean(){
        return new ModelMapper();
    }
}
