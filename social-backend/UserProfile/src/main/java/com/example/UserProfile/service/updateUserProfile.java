package com.example.UserProfile.service;

import com.example.UserProfile.configuration.Credentials;
import com.example.UserProfile.request.userAccUpdateRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.*;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;


@Service
public class updateUserProfile {
	@Autowired
	private Credentials cre;
    private static final Logger logger = LogManager.getLogger(updateUserProfile.class);
    public Boolean updateProfileProcess(userAccUpdateRequest request) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://localhost:3000/updateProfile";
            
            // Create JSON payload
            Map<String, Object> jsonPayload = new HashMap<>();
            jsonPayload.put("userID", request.getUserID());
            jsonPayload.put("job", request.getJob());
            jsonPayload.put("address", request.getAddress());
            jsonPayload.put("bio", request.getBio());
            jsonPayload.put("first_name", request.getFirst_name());
            jsonPayload.put("last_name", request.getLast_name());
            jsonPayload.put("mobile", "+" + request.getMobile());

            // Handle birth date and age calculation
            if (request.getBirth_date() != null) {
                LocalDate birthDate = request.getBirth_date().toLocalDateTime().toLocalDate();
                jsonPayload.put("birth_date", birthDate.toString());
                
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                jsonPayload.put("age", age);
            } else {
                jsonPayload.put("birth_date", null);
                jsonPayload.put("age", 0);
            }

            // Convert to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(jsonPayload);

            // Set headers - CRITICAL FIX HERE
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // Explicit content type
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>() {}
            );
            
            return true;
        } catch(Exception e) {
            logger.error("Error updating profile", e);
            throw new Exception("Error occurred when updating the profile: " + e.getMessage(), e);
        }
    }
    public Boolean sendCodePart(String email) throws Exception{
    	try {
            SecureRandom secureRandom = new SecureRandom();
            int randomNumber = secureRandom.nextInt(100);
            MailjetClient client = cre.getMailConnection();
            TransactionalEmail message1 = TransactionalEmail
                    .builder()
                    .to(new SendContact(email, "stanislav"))
                    .from(new SendContact("saliyaautocare52@gmail.com", "Mailjet integration test"))
                    .htmlPart("<h1>Your verification code : " + randomNumber + "</h1>")
                    .subject("This is the subject")
                    .trackOpens(TrackOpens.ENABLED)
                    .header("test-header-key", "test-value")
                    .customID("custom-id-value")
                    .build();
            SendEmailsRequest request = SendEmailsRequest
                    .builder()
                    .message(message1) // you can add up to 50 messages per request
                    .build();
            SendEmailsResponse response = request.sendWith(client);

    		return true;
    	}catch(Exception e) {
    		throw new Exception("Error occured when sending code (updateUserProfile.jsva) : " + e.toString());
    	}
    }
}







