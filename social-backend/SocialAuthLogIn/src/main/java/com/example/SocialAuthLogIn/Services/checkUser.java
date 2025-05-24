package com.example.SocialAuthLogIn.Services;

import com.example.SocialAuthLogIn.Entity.users;
import com.example.SocialAuthLogIn.Repo.usersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


public class checkUser {
//    @Autowired
//    private usersRepo usersRepoGetRepo;
    public Object checkUserDetailsByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:3000/custom1/"+email.toString();
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
//    public List<users> fetchAll(){
//        return usersRepoGetRepo.testQuery();
//    }
}
