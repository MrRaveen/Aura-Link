package com.example.login.Service;

import com.example.login.Entity.users;
import com.example.login.Entity.usersForIDget;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GetUserID {
public int getProcess(String username){
   try{
       //call the request
       RestTemplate restTemplate = new RestTemplate();
       //converters
       List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
       MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
       converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
       messageConverters.add(converter);
       restTemplate.setMessageConverters(messageConverters);

       String uri = "http://localhost:3000/checkUserName/"+username;
       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
       headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
       HttpEntity<String> entity = new HttpEntity<>(headers);

       ResponseEntity<usersForIDget> ProfileResult =
               restTemplate.exchange(
                       uri,
                       HttpMethod.GET,
                       entity,
                       usersForIDget.class
               );

       usersForIDget recievedObj = ProfileResult.getBody();
       if(recievedObj.getUser_id() == 0){
           return 0;
       }else{
           return recievedObj.getUser_id();
       }
   }catch(Exception e){
       throw new RuntimeException("Error while getting user ID : " + e.toString());
   }
}
}
