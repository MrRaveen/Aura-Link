package com.example.socialCreateAccountService.Controller;

import com.example.socialCreateAccountService.Entity.user_profiles;
import com.example.socialCreateAccountService.Entity.users;
import com.example.socialCreateAccountService.Services.CreateAccount;
import com.example.socialCreateAccountService.Services.CreateProfile;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalTime;
import java.util.*;
import java.util.Map;

//test1
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

//test1

@RestController
public class AccessPoint {
    @Autowired
    private CreateAccount create1;
    @Autowired
    private CreateProfile createProfile1;
    private final OAuth2AuthorizedClientService authorizedClientService;

    private static final String APPLICATION_NAME = "AuraLink";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Arrays.asList(PeopleServiceScopes.USER_PHONENUMBERS_READ); //changed 4
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    public AccessPoint(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws Exception {
        // Load client secrets.
        InputStream in = AccessPoint.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(5173).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @GetMapping("/")
    public String getUser(OAuth2AuthenticationToken authentication) throws Exception {
        //get the primary details
        String fname = authentication.getPrincipal().getAttributes().get("given_name").toString();
        String lname = authentication.getPrincipal().getAttributes().get("family_name").toString();
        String picUrl = authentication.getPrincipal().getAttributes().get("picture").toString();
        String email = authentication.getPrincipal().getAttributes().get("email").toString();
        //create the user
        LocalTime currentTime = LocalTime.now();
        users createUser  = new users("",email,"",currentTime);
        Map<String, Object> newUser = create1.createAccount(createUser);
        users u1 = (users) newUser.get("saved_user");
        System.out.println("EMAIL :: " + u1.getEmail());
        user_profiles createProfile = new user_profiles(u1,fname,lname,"",picUrl,"","",null,currentTime,0,0,"","");
        Map<String, Object> out = createProfile1.createAccount(createProfile);
//        // Build a new authorized API client service.
//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        PeopleService service =
//                new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                        .setApplicationName(APPLICATION_NAME)
//                        .build();
//        // Request 10 connections.
//        ListConnectionsResponse response = service.people().connections()
//                .list("people/me")
//                .setPageSize(10)
//                .setPersonFields("names,emailAddresses,phoneNumbers") //vhanged 3
//                .execute();
//        // Print display name of connections if available.
//        List<Person> connections = response.getConnections();
//        if (connections != null && connections.size() > 0) {
//            for (Person person : connections) {
//                List<PhoneNumber> names = person.getPhoneNumbers(); //changed 1
//                if (names != null && names.size() > 0) {
//                    System.out.println("Name: " + person.getPhoneNumbers().get(0).getValue().toString());
////                            .getDisplayName()); //changed 2
//                } else {
//                    System.out.println("No names available for connection.");
//                }
//            }
//        } else {
//            System.out.println("No connections found.");
//        }
        if(!u1.getEmail().isEmpty()){
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background: #f0f2f5; display: flex; justify-content: center; padding: 20px; }" +
                    ".card { background: white; border-radius: 10px; box-shadow: 0 2px 15px rgba(0,0,0,0.1); padding: 30px; max-width: 400px; width: 100%; }" +
                    ".profile-img { width: 100px; height: 100px; border-radius: 50%; object-fit: cover; margin: 0 auto 20px; display: block; }" +
                    ".info-item { margin: 15px 0; padding: 10px; background: #f8f9fa; border-radius: 5px; }" +
                    ".label { color: #6c757d; font-weight: bold; margin-right: 10px; }" +
                    ".btn-container { text-align: center; margin-top: 20px; }" +
                    ".dashboard-btn { background-color: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 5px; text-decoration: none; font-size: 16px; cursor: pointer; }" +
                    ".dashboard-btn:hover { background-color: #0056b3; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='card'>" +
                    "<img src='" + picUrl + "' class='profile-img' alt='Profile picture'>" +
                    "<div class='info-item'><span class='label'>First Name:</span>" + fname + "</div>" +
                    "<div class='info-item'><span class='label'>Last Name:</span>" + lname + "</div>" +
                    "<div class='info-item'><span class='label'>Email:</span>" + email + "</div>" +
                    "<div class='btn-container'>" +
                    "<a href='http://localhost:5173/mainMenuTo' class='dashboard-btn'>Return to Dashboard</a>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

        }else{
            return "Error occurred in account creation";
        }
    }
}
