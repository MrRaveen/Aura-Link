package com.example.UserProfile.service;

import com.example.UserProfile.entity.PostComments;
import com.example.UserProfile.response.PostAllContainers;
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
public class GetCommentsPost {
    public List<PostComments> getCommentProcess(int postId) {
       try{
           RestTemplate restTemplate = new RestTemplate();
           //converters
           List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
           MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
           converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
           messageConverters.add(converter);
           restTemplate.setMessageConverters(messageConverters);

           String uri = "http://localhost:3000/getPostComments/"+postId;
           HttpHeaders headers = new HttpHeaders();
           headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
           headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
           HttpEntity<String> entity = new HttpEntity<>(headers);
           ResponseEntity<List<PostComments>> responseEntity =
                   restTemplate.exchange(
                           uri,
                           HttpMethod.GET,
                           null,
                           new ParameterizedTypeReference<List<PostComments>>() {}
                   );
           List<PostComments> allComments = responseEntity.getBody();
           return allComments;
       }catch(Exception e){
           System.out.println("Error occurred in GetCommentsPost.java" + e.toString());
           return null;
       }
    }
}
