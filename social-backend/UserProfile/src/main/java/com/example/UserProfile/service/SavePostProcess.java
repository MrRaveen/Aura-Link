package com.example.UserProfile.service;

import com.example.UserProfile.entity.Users;
import com.example.UserProfile.entity.postEntity;
import com.example.UserProfile.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SavePostProcess {
    @Autowired
    PostRepo postRepo;

    private static final String TOPIC = "my_topic";
    private final KafkaTemplate<String, postEntity> kafkaTemplate;

    public SavePostProcess(KafkaTemplate<String, postEntity> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public int savePostProcess(postEntity post,int special) { //saves only main data
        try{
            //save to the main table
            Users user = new Users();
            user.setUser_id(post.getUser_id().getUser_id());
            postEntity saveObj1 = new postEntity(
                    post.getContent_type(),
                    post.getDate(),
                    post.getDescription(),
                    post.getTime(),
                    post.getTitle(),user);
            postEntity outputData = postRepo.save(saveObj1);
            if(special == 1){
                //TODO:no need (do that in the image sending part)
                System.out.println("no need");
            }else{
                //send message
                kafkaTemplate.send(TOPIC, outputData);
                System.out.println("send message");
            }
            return outputData.getPostid();
        }catch(Exception e){
            throw new RuntimeException("Error occured in Save post process : " + e.toString());
        }
    }
}
