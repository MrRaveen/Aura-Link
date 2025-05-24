package com.example.UserProfile.service;

import com.example.UserProfile.entity.postEntity;
import com.example.UserProfile.repository.PostRepo;
import com.example.UserProfile.request.updatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class updateService {
    @Autowired
    private PostRepo postRepo;
    @Transactional
    public postEntity updateProcess(int postId, updatePostRequest data)throws Exception{
        try{
            postEntity postGot = postRepo.findById(postId)
                    .orElseThrow(() -> new RuntimeException("Post not found"));
            postGot.setTitle(data.getTitle());
            postGot.setDescription(data.getDescription());
         return postRepo.save(postGot);
        }catch(Exception e){
            throw new Exception("Error updating post : " + e.getMessage().toString());
        }
    }
}
