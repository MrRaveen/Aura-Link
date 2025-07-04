package com.example.notificationService.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.notificationService.Entity.notificationsMongo;
import com.example.notificationService.Repository.notificationRepoMongo;
import com.example.notificationService.Response.NotificationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationsService {
	@Autowired
	public notificationRepoMongo test;
	//get the notifications
	public List<NotificationResponse> getNotifications(int userID) throws Exception{
		try {
			List<NotificationResponse> contentResponse = new ArrayList<NotificationResponse>();
			//converters
			RestTemplate restTemplate = new RestTemplate();  
	        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	        //Add the Jackson Message converter
	        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	        // Note: here we are making this converter to process any kind of response,
	        // not only application/*json, which is the default behaviour
	        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
	        messageConverters.add(converter);
	        restTemplate.setMessageConverters(messageConverters);

			//get the notifications
			List<notificationsMongo> output = test.findByRecipientUserId(userID);
			output.forEach(obj -> {
				//get the profile URL
		        String uri = "http://localhost:3000/getProfileData/"+userID;
		        HttpHeaders headers = new HttpHeaders();
		        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		        HttpEntity<String> entity = new HttpEntity<>(headers);
		        ResponseEntity<com.example.notificationService.Entity.User_profiles> ProfileResult = restTemplate.exchange(uri, HttpMethod.GET, entity, com.example.notificationService.Entity.User_profiles.class);
		        com.example.notificationService.Entity.User_profiles outObj = ProfileResult.getBody();//user profile data
		        //create the response object
		        NotificationResponse creatingObject = new NotificationResponse(String.valueOf(obj.getID()), String.valueOf(obj.getType()), obj.getHeader(), obj.getBody(), obj.getCreatedDate(), obj.getRecipientUserId(), obj.getActorUserId(), obj.isRead(), outObj.getProfile_pic_url());
		        contentResponse.add(creatingObject);
			});
			return contentResponse;
		}catch(Exception e) {
			throw new Exception("Error occured when selecting data (NotificationsService.java): " + e.toString());
		}
	}
	//update reading status
	public Boolean updateReadStatus(String notificationID) throws Exception {
		try {
			
		   return false;
		}catch(Exception e) {
			throw new Exception("Error occured when updating the status (NotificationsService.java): " + e.toString());
		}
	}
}
