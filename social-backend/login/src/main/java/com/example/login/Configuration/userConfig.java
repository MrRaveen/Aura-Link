package com.example.login.Configuration;

import com.example.login.Service.login_service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class userConfig {
    /*
  * In a Spring Boot application, defining beans using @Bean inside a @Configuration class like UserConfig explicitly tells Spring to create and manage instances of these objects (for AutoWire).
  *
  * Spring Boot automatically scans for components annotated with @Component, @Service, @Repository, etc., and registers them as Spring Beans.
    If you annotate UserService with @Service, Spring will automatically manage it without needing a @Bean declaration.
*
* Why Use @Bean in UserConfig?
*    When You Don’t Want to Use @Service, @Component, or @Repository
*        If UserService is from a third-party library or lacks annotations, you can manually declare it as a Spring Bean.
*    When You Need to Customize Bean Initialization
*        If a class requires custom setup before being used, defining it in a @Configuration class gives you more control.
*    To Create and Share a Singleton Bean
*        Spring Beans by default are singletons, meaning only one instance exists for the entire application.
       Example: ModelMapper is a utility that should be shared across the app.
*    When You Need Conditional Bean Creation
   * */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public login_service logInBean() {
        return new login_service();
    }
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
