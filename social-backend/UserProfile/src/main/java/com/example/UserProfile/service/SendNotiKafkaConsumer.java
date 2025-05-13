package com.example.UserProfile.service;

import com.example.UserProfile.entity.User_profiles;
import com.example.UserProfile.entity.notificationsMongo;
import com.example.UserProfile.entity.postEntity;
import com.example.UserProfile.repository.notificationRepoMongo;
import com.example.UserProfile.response.followersResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class SendNotiKafkaConsumer {
    @Autowired
    private notificationRepoMongo notificationEntrance;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    //to get all the details
    @Autowired
    private SimpUserRegistry simpUserRegistry;
    //consumes the kafka message
    @KafkaListener(topics = "my_topic", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(postEntity message) {
       try{
           //to test
           System.out.println("Message received: " + message.getTitle());
           //get the followers of the user
           RestTemplate restTemplate = new RestTemplate();
           //converters
           List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
           MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
           converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
           messageConverters.add(converter);
           restTemplate.setMessageConverters(messageConverters);
           String uri = "http://localhost:3000/getFollowsers/"+message.getUser_id().getUser_id();
           HttpHeaders headers = new HttpHeaders();
           headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
           headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
           HttpEntity<String> entity = new HttpEntity<>(headers);
           ResponseEntity<List<followersResponse>> responseEntity =
                   restTemplate.exchange(
                           uri,
                           HttpMethod.GET,
                           null,
                           new ParameterizedTypeReference<List<followersResponse>>() {}
                   );
           List<followersResponse> allFollowers = responseEntity.getBody();
           //save the notification in the mongoDB
           LocalDate currentDate = LocalDate.now();
           Timestamp timestamp = new Timestamp(System.currentTimeMillis());
           notificationsMongo enumInput = new notificationsMongo();

           //get the profile data
           String uri2 = "http://localhost:3000/getProfileData/"+message.getUser_id().getUser_id();
           HttpHeaders headers2 = new HttpHeaders();
           headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
           headers2.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
           HttpEntity<String> entity2 = new HttpEntity<>(headers2);
           ResponseEntity<User_profiles> ProfileResult = restTemplate.exchange(uri2, HttpMethod.GET, entity2, User_profiles.class);
           User_profiles outObj = ProfileResult.getBody();//user profile data

           for(followersResponse tempHolder : allFollowers){
               notificationsMongo notificationSaveObject = new notificationsMongo(
                       tempHolder.getFollower(),
                       message.getUser_id().getUser_id(),
                       notificationsMongo.NotificationType.POST,
                       outObj.getFirst_name() + " " + outObj.getLast_name() + " posted a new post",
                       message.getTitle(),
                       false,currentDate,
                       timestamp);
               notificationsMongo resultObj = notificationEntrance.save(notificationSaveObject);

               //send the socket message
               String currentFollowerID = String.valueOf(tempHolder.getFollower());
               SimpUser isOnlineCheck = simpUserRegistry.getUser(currentFollowerID);
               if(isOnlineCheck != null){
                   simpMessagingTemplate.convertAndSendToUser(isOnlineCheck.getName(),"/secured/user/queue/specific-user","You have one notification from "+outObj.getFirst_name() + " " + outObj.getLast_name());
               }
           }
       }catch(Exception e){
           throw new RuntimeException("Error occured while processing post message : " + e.toString());
       }

    }
}
