package com.example.UserProfile.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.UserProfile.configuration.Credentials;
import com.example.UserProfile.entity.CheckEmailVerificationMongo;
import com.example.UserProfile.entity.User_profiles;
import com.example.UserProfile.entity.Users;
import com.example.UserProfile.repository.CheckEmailVerificationMongoRepo;
import com.example.UserProfile.repository.UsersRepo;
import com.example.UserProfile.request.userAccUpdateRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.andrewoma.dexx.collection.internal.base.Break;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.*;
import com.mailjet.client.transactional.response.SendEmailsResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;


@Service
public class updateUserProfile {
	@Autowired
	private Credentials cre;
	@Autowired
	private UsersRepo userRepo;
    @Autowired
    private BlobServiceClient blobServiceClient;
	@Autowired
	private CheckEmailVerificationMongoRepo mongoRepoObj;
    private static final Logger logger = LogManager.getLogger(updateUserProfile.class);
    
    //function to send the email confirmation
    public void sendTheEmailToUser(String email, int userID) throws Exception {
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
            //save to the mongo db 
            CheckEmailVerificationMongo passObj = new CheckEmailVerificationMongo(String.valueOf(userID), String.valueOf(randomNumber));
            mongoRepoObj.save(passObj);
            SendEmailsResponse response = request.sendWith(client);
    	}catch(Exception e) {
    		throw new Exception("Error occured when sending the email (fun --> sendTheEmailToUser): " + e.toString());
    	}
    }
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
    public Boolean sendCodePart(int userID) throws Exception{
    	try {
    		//get the userEmail
    		Optional<Users> foundUser = userRepo.findById(userID);
    		if(foundUser.isEmpty()) {
    			throw new Exception("The user not found");
    		}else {
    			AtomicReference<String> gotMail = new AtomicReference<>("");
        		foundUser.ifPresent(Users -> {
        		    gotMail.set(Users.getEmail());
        		});
    			//send the email to user
        		sendTheEmailToUser(gotMail.toString(),userID);
//                SecureRandom secureRandom = new SecureRandom();
//                int randomNumber = secureRandom.nextInt(100);
//                MailjetClient client = cre.getMailConnection();
//                TransactionalEmail message1 = TransactionalEmail
//                        .builder()
//                        .to(new SendContact(gotMail.toString(), "stanislav"))
//                        .from(new SendContact("saliyaautocare52@gmail.com", "Mailjet integration test"))
//                        .htmlPart("<h1>Your verification code : " + randomNumber + "</h1>")
//                        .subject("This is the subject")
//                        .trackOpens(TrackOpens.ENABLED)
//                        .header("test-header-key", "test-value")
//                        .customID("custom-id-value")
//                        .build();
//                SendEmailsRequest request = SendEmailsRequest
//                        .builder()
//                        .message(message1) // you can add up to 50 messages per request
//                        .build();
//                //save to the mongo db 
//                CheckEmailVerificationMongo passObj = new CheckEmailVerificationMongo(String.valueOf(userID), String.valueOf(randomNumber));
//                mongoRepoObj.save(passObj);
//                SendEmailsResponse response = request.sendWith(client);
        		return true;
    		}
    	}catch(Exception e) {
    		throw new Exception("Error occured when sending code (updateUserProfile.jsva) : " + e.toString());
    	}
    }
    public String verifyAndUpdate(String verificationCode, userAccUpdateRequest updateRequestObj) throws Exception{
    	try {
    		//find the user from mongodb
    		/*
    		 * or not throw an excep
    		 * then check the code
    		 * if wrong, return error str
    		 * */
			AtomicReference<Boolean> status = new AtomicReference<>(false);
    		Optional<CheckEmailVerificationMongo> findByID = mongoRepoObj.findById(String.valueOf(updateRequestObj.getUserID()));
    		if(findByID.isEmpty()) {
    			return "Cannot find the user. User credentials update failed";
    		}else {
    			findByID.ifPresent(verification -> {
        		   String selectedID = verification.getVerificationID();
    				if(verificationCode.equals(selectedID)) {
        		    	 Users user = userRepo.findById(updateRequestObj.getUserID())
        		                 .orElseThrow(() -> new EntityNotFoundException("User not found"));
        		    	 user.setUsername(updateRequestObj.getUserName());
        		    	 user.setPassword_hash(updateRequestObj.getPassword());
        		    	 userRepo.save(user);
        		    	 status.set(true);
        		    }else {
        		    	status.set(false);
        		    }
        		});
    		}
    		if(status.get() == true) {
    			//remove the mongodb data
    			mongoRepoObj.deleteById(String.valueOf(updateRequestObj.getUserID()));
        		return "Password updated";
    		}else {
        		return "Invalid verification code";
    		}
    	}catch(Exception e) {
    		throw new Exception("Error occured when updating (updateUserProfile.java) : " + e.toString());
    	}
    }
    //updates the email of the user
    public String updateEmail(String email, int userID, int mode, String verificationCode) throws Exception {
    	try {
    		if(mode == 1) {
    			//send code
    			sendTheEmailToUser(email,userID);
    			return "An email is sent to " + email + " email address";
    		}else if(mode == 2) {
    			//verify the code
    			AtomicReference<Boolean> status = new AtomicReference<>(false);
        		Optional<CheckEmailVerificationMongo> findByID = mongoRepoObj.findById(String.valueOf(userID));
        		if(findByID.isEmpty()) {
        			return "Cannot find the user. User email update failed";
        		}else {
        			findByID.ifPresent(verification -> {
            		   String selectedID = verification.getVerificationID();
        				if(verificationCode.equals(selectedID)) {
        					//update email by userID
        					Users user = userRepo.findById(userID)
           		                 .orElseThrow(() -> new EntityNotFoundException("User not found"));
        					user.setEmail(email);
        					userRepo.save(user);
            		    	 status.set(true);
            		    }else {
            		    	status.set(false);
            		    }
            		});
        		}
        		if(status.get() == true) {
        			//remove the mongodb data
        			mongoRepoObj.deleteById(String.valueOf(userID));
            		return "Email updated";
        		}else {
            		return "Invalid varification code";
        		}
    		}else {
    			return "Invalid mode code";
    		}
    	}catch(Exception e) {
    		throw new Exception("Error occured when updating email (updateUserProfile.java) : " + e.toString());
    	}
    }
    //get the profile info
    public User_profiles getProfileProcess(int userID) throws Exception {
    	try {
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
    		    return outObj;
    	}catch(Exception e) {
    		throw new Exception("Error occured when getting profile data (updateUserProfile.java) : " + e.toString());
    	}
    }
    //update the profile image
    public String updateProfileImageByID(MultipartFile file,int userID) throws Exception {
    	try {
    		/*
    		 * create the image name using userID
    		 * remove the image from blob
    		 * insert the new image
    		 * */
    		String fileName = "userpic"+userID+".jpg";
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("profileimages");
            boolean result = containerClient.getBlobClient(fileName).deleteIfExists();
            //insert new img
            BlobClient blobClient = containerClient.getBlobClient(fileName);
            // Upload the file
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            if(!blobClient.getBlobUrl().isEmpty()) {
            	return "Image updated";
            }else {
        		return "Image not updated";
            }
    	}catch(Exception e) {
    		throw new Exception("Error occured when updating profile image (updateUserProfile.java) : " + e.toString());
    	}
    }
}







