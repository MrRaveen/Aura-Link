package com.example.UserProfile.service;

import com.example.UserProfile.entity.*;
import com.example.UserProfile.repository.UsersRepo;
//import com.github.andrewoma.dexx.collection.ArrayList;
import com.example.UserProfile.response.OtherInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.converter.HttpMessageConverter;
import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserProcess {
    @Autowired
    private UsersRepo usersRepoObj;
    public OtherInfoRes getActivityInfo(int userID) {
        //get the email by ID
        Optional<Users> result = usersRepoObj.findById(userID);
        AtomicReference<String> gotMail = new AtomicReference<>("");
        result.ifPresent(Users -> {
            gotMail.set(Users.getEmail());
        });

        RestTemplate restTemplate = new RestTemplate();
        //converters
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        //get the other details
        String uri = "http://localhost:3000/getProfileData/"+userID;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<User_profiles> ProfileResult = restTemplate.exchange(uri, HttpMethod.GET, entity, User_profiles.class);
        User_profiles outObj = ProfileResult.getBody();//user profile data

        //get the post count of the specific user
        //get the post count
        String postUri2 = "http://localhost:3000/getPostCount/"+userID;
        HttpHeaders postHeader = new HttpHeaders();
        postHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        postHeader.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> postEntity = new HttpEntity<>(postHeader);
        ResponseEntity<PostCount> PostResult = restTemplate.exchange(postUri2, HttpMethod.GET, postEntity, PostCount.class);
        PostCount countObj = PostResult.getBody();//post Count
        //get the following count
        postUri2 = "http://localhost:3000/getFollowCount/"+userID;
        ResponseEntity<FollowerCount> FollowerObj = restTemplate.exchange(postUri2, HttpMethod.GET, postEntity, FollowerCount.class);
        FollowerCount FollowObj1 = FollowerObj.getBody();//follow Count
        //get the followers count
        postUri2 = "http://localhost:3000/getFollowingCount/"+userID;
        ResponseEntity<FollowersCount> FollowersObj = restTemplate.exchange(postUri2, HttpMethod.GET, postEntity, FollowersCount.class);
        FollowersCount FollwersObj1 = FollowersObj.getBody();//followers Count

        OtherInfoRes resObj1 = new OtherInfoRes(outObj.getJob(),outObj.getBio(),outObj.getBirth_date(),outObj.getFirst_name(),outObj.getLast_name(),outObj.getProfile_pic_url(),gotMail.get(),FollwersObj1.getFollowerscountNumber(),countObj.getCount(), FollowObj1.getFollowCount());
        //test output
        //System.out.println("Post count :: " + countObj.getCount() + "\nFollow :: " + FollowObj1.getFollowCount() + "\nFollowers count :: " + FollwersObj1.getFollowerscountNumber());
        return resObj1;
    }
}
