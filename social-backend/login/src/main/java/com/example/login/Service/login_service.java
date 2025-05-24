package com.example.login.Service;

import com.example.login.Entity.users;
import com.example.login.Repo.usersRepo;
import com.example.login.Requests.logIn_request;
import com.example.login.Responses.userResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
public class login_service implements UserDetailsService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private usersRepo repo;
    @Transactional
    public String logInProcess(logIn_request us){
        List<users> user = repo.findAll();
        String[] arr1 = new String[2];
        arr1[0] = "NONE";
        String userName = us.getUsername();
        String pass = us.getPassword_hash();
        user.forEach((p)->{
            if(p.getUsername().equals(userName) && p.getPassword_hash().equals(pass)){
                arr1[0] = "APPROVED";
            }
        });
        return arr1[0];
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<users> user = repo.findAll();
        String[] arr1 = new String[2];
//        arr1[0] = "NONE";
//        String userName = us.getUsername();
//        String pass = us.getPassword_hash();
        user.forEach((p)->{
            if(p.getUsername().equals(username)){
                arr1[0] = p.getUsername();
                arr1[1] = p.getPassword_hash();
            }
        });
        return new User(
                arr1[0],
                arr1[1],
                new ArrayList<>()
        );
    }
}
