package com.example.savePost2.Service;

import com.example.savePost2.Entity.FollowerInfo;
import com.example.savePost2.Entity.PostContent;
import com.example.savePost2.Entity.Posts;
import com.example.savePost2.Repo.FollowerInfoRepo;
import com.example.savePost2.Repo.PostContentRepo;
import com.example.savePost2.Repo.PostsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SavePost {
    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private PostContentRepo postContentRepo;
    @Autowired
    private FollowerInfoRepo followerInfoRepo;
    public Posts savePost(Posts post, PostContent postContent, int user_id) throws Exception {
        Posts p1 = null;
        try{
            p1 = postsRepo.save(post);
            postContent.setPost(p1);
            PostContent p2 = postContentRepo.save(postContent);
            //azure part
            //get the followers
            LinkedList<Optional<FollowerInfo>> listFollowerInfo = new LinkedList<>();
            listFollowerInfo.add(followerInfoRepo.findById(user_id)); //custom query usage
            System.out.println(listFollowerInfo);
            //kafka part
            //insert to the mongo DB
        }catch(Exception e){
            throw new Exception("Error saving post");
        }
        return p1;
    }
}
