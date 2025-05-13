package com.example.savePost2.Controller;

import com.example.savePost2.Entity.PostContent;
import com.example.savePost2.Entity.Posts;
import com.example.savePost2.Request.PostContentRequest;
import com.example.savePost2.Request.PostRequest;
import com.example.savePost2.Request.allRequest;
import com.example.savePost2.Service.SavePost;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@SpringBootApplication
@RequestMapping(path = "/api/feed")
public class AccessPoint {
    public enum ContentType{
        VIDEO,
        IMAGE,
        TEXT,
        VIDEO_IMAGES
    }
    @Autowired
    private SavePost sv1;
    @GetMapping("/savePost")
    public String savePost(@RequestBody allRequest r1) throws Exception {
        PostRequest p = r1.getPc2();
        PostContentRequest p2 = r1.getPc1();
        Posts getContenttype = new Posts();
        String ct1 = p.getContentType().toString();
        Posts.ContentType contentType;
        if(ct1 == "VIDEO"){
            contentType = Posts.ContentType.VIDEO;
        }else if(ct1 == "IMAGE"){
            contentType = Posts.ContentType.IMAGE;
        }else if(ct1 == "TEXT"){
            contentType = Posts.ContentType.TEXT;
        }else if(ct1 == "VIDEO_IMAGES"){
            contentType = Posts.ContentType.VIDEO_IMAGES;
        }else{
            contentType = null;
        }
        getContenttype.setContentType(contentType);
        Posts p3 = new Posts(contentType,p.getDate(),p.getTime(),p.getTitle(),p.getDescription(),p.getUser_id());
        PostContent p4 = new PostContent(p2.getType(),p2.getMediaName(),p2.getMetaData(),p2.getPost());
        Posts savedPost = sv1.savePost(p3,p4,19);
        return "test1";
    }
}
