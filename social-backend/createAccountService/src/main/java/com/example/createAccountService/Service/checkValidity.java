package com.example.createAccountService.Service;

import com.example.createAccountService.Entity.users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class checkValidity {
    public Object checkValidityUserName(String usName){
        //send the request to node api
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:3000/checkUserName/"+usName;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<?> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, users.class);
        if(result.getBody() != null) {
            return result.getBody();
        }else{
            return null;
        }
    }
    public Object checkValidityMail(String mail){
        //send request to node api
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:3000/checkMail/"+mail;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<?> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, users.class);
        if(result.getBody() != null) {
            return result.getBody();
        }else{
            return null;
        }
    }
}
