package com.example.notificationService.Configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public CookieSerializer cookieSerializer() {
	    DefaultCookieSerializer serializer = new DefaultCookieSerializer();
	    serializer.setCookieName("JSESSIONID"); // Same cookie name
	    return serializer;
	}
	  @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                        .requestMatchers("/api/notification/getNotifications").authenticated() // Require authentication
	                        .requestMatchers("/api/notification/updateNotStatusByClick").authenticated()
	                        .requestMatchers("/api/notification/getNotifications").authenticated()
	                        .anyRequest().permitAll() // Allow other endpoints
	                )
	                .sessionManagement(session -> session
	                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Enable sessions
	                )
	                .csrf(csrf -> csrf.disable()); // Disable CSRF for testing (re-enable later)

	        return http.build();
	    }
	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Allow all origins
	        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
	        config.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
	        
	        config.setAllowCredentials(true);
	        config.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config); // Apply to all endpoints
	        return source;
	    }
}
