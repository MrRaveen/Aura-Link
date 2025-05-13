package com.example.UserProfile.service;

import com.example.UserProfile.repository.PostRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostRemoveProcess {
    @Autowired
    private PostRepo postRepo;
    public String process(int postId) {
        try{
            postRepo.deleteById(postId);
            return "Post Removed Successfully";
        }catch(Exception e){
            return "Failed to return data : " + e.toString();
        }
    }
}
