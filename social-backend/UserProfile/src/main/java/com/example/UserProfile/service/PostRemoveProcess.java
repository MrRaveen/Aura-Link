package com.example.UserProfile.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.UserProfile.entity.PostContentEntity;
import com.example.UserProfile.entity.PostContents;
import com.example.UserProfile.repository.PostRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostRemoveProcess {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private BlobServiceClient blobServiceClient;
    public String process(int postId) throws Exception {
        try{
            RestTemplate restTemplate = new RestTemplate();
            //converters
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            //remove the post comments
            String uri = "http://localhost:3000/removeComments/"+postId;
            ResponseEntity<?> responseEntity =
                    restTemplate.exchange(
                            uri,
                            HttpMethod.DELETE,
                            null,
                            new ParameterizedTypeReference<>() {}
                    );
            //remove the post reactions
            uri = "http://localhost:3000/removeReactions/"+postId;
            ResponseEntity<?> responseEntity2 =
                    restTemplate.exchange(
                            uri,
                            HttpMethod.DELETE,
                            null,
                            new ParameterizedTypeReference<>() {}
                    );
            //get the post content names
            uri = "http://localhost:3000/getPostContentName/"+postId;
            ResponseEntity<List<PostContents>> responseEntity3 = restTemplate.exchange(
              uri,
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<PostContents>>() {}
            );
            List<PostContents>contentList = responseEntity3.getBody();//stores the content name list
            //remove the post contents (table)
            uri = "http://localhost:3000/removePostDetailsAll/"+postId;
            ResponseEntity<?> responseEntity4 = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            //remove the actual contents (azure)
            contentList.forEach(postContentEntity -> {
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("postcontents");
                boolean result = containerClient.getBlobClient(postContentEntity.getMedia_name()).deleteIfExists();
            });
            postRepo.deleteById(postId);
            return "Post Removed Successfully";
        }catch(Exception e){
            throw new Exception("Error occurred when removing post (service) : " + e.getMessage());
        }
    }
}
