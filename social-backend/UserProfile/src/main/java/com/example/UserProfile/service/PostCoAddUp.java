package com.example.UserProfile.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.UserProfile.entity.PostComments;
import com.example.UserProfile.entity.PostContentEntity;
import com.example.UserProfile.entity.postEntity;
import com.example.UserProfile.repository.PostContentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostCoAddUp {
    @Autowired
    private PostContentRepo postContentRepo;
    @Autowired
    private BlobServiceClient blobServiceClient;

    @CacheEvict(value = "posts", key = "#userId")
    public void evictUserPostsCache(int userId){
        System.out.println("userID : " + userId);
    }
    @Transactional
    public String addingProcess(MultipartFile file, int postID, int userID) {
        try{
            //add the details to the table
            String fileName = file.getOriginalFilename();//get the name
            String type = file.getContentType(); //get the file type
            //meta data
            long sizeMeta = file.getSize();
            sizeMeta = sizeMeta / 1000;
            String metaData = "{\"size\":" +"\""+ String.valueOf(sizeMeta) +"\""+ ",\"fileName\":" +"\""+ fileName +"\""+ "}";
            //post ID
            postEntity postIDinput = new postEntity();
            postIDinput.setPostid(postID);
            //adding process to table
            PostContentEntity dataSet = new PostContentEntity(fileName,metaData,type,postIDinput);
            PostContentEntity newContent = postContentRepo.save(dataSet);
            //update the name
            int newContentID = newContent.getContentid();
            String newContentName = "CONT" + newContentID + ".jpg";
            PostContentEntity updateDataOut = postContentRepo.findById(newContentID)
                    .orElseThrow(() -> new RuntimeException("Post not found"));
            updateDataOut.setMedia_name(newContentName);
            PostContentEntity updatedResultPost = postContentRepo.save(updateDataOut);
            //add to the blob
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("postcontents");
            if (!containerClient.exists()) {
                containerClient.create();
            }
            BlobClient blobClient = containerClient.getBlobClient(newContentName);
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            if(blobClient.getBlobUrl().isEmpty()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //rollback
                return "Blob did not upload";
            }else{
                //invalidate cache
                evictUserPostsCache(userID);
                return "Blob uploaded";
            }
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //rollback
            throw new RuntimeException("Error adding post content (update section) : " + e.toString());
        }
    }
    public boolean checkEligibility(int postID){
        RestTemplate restTemplate = new RestTemplate();
        //converters
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        String uri = "http://localhost:3000/postContentCount/"+postID;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<String>() {}
                );
        String count = responseEntity.getBody();
        if(Integer.parseInt(count) > 10){
            return false;
        }else{
            return true;
        }
    }
}
