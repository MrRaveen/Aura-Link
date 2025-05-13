package com.example.UserProfile.controller;

import com.example.UserProfile.entity.PostComments;
import com.example.UserProfile.entity.Users;
import com.example.UserProfile.entity.postEntity;
import com.example.UserProfile.entity.posts;
import com.example.UserProfile.request.CreatePostRequest;
import com.example.UserProfile.request.updatePostRequest;
import com.example.UserProfile.response.OtherInfoRes;
import com.example.UserProfile.response.PostAllContainers;
import com.example.UserProfile.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userAccount")
@CrossOrigin
public class AccessPoint {
    @Autowired
    private UserProcess userProcess;
    @Autowired
    private GetAllPostService getAllPostService;
    @Autowired
    private PostRemoveProcess postRemoveProcess;
    @Autowired
    private GetCommentsPost getCommentsPost;
    @Autowired
    private updateService updateGate;
    @Autowired
    private RemoveTargetResource removeTargetResource;
    @Autowired
    private PostCoAddUp postCoAddUp;
    @Autowired
    private SavePostProcess savePostProcess;

    //get the profile information (public)
    @GetMapping("/getAllAccInfo")
    public ResponseEntity<OtherInfoRes> getAllAccPrimaryInfo(@RequestParam int userId) {
        OtherInfoRes versionObj = userProcess.getActivityInfo(userId);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES).cachePublic())
                .body(versionObj);
    }
    //get the posts according to the user
    @GetMapping("/getAllPosts")
    public PostAllContainers getAllPosts(@RequestParam int userId) {
        PostAllContainers result = getAllPostService.GetAllPosts(userId);
        return result;
    }
    //remove posts
    @DeleteMapping("/removePost")
    public String removePost(@RequestParam int postId) {
        String result = postRemoveProcess.process(postId);
        return result;
    }
    //get the comments
    @GetMapping("/getComments")
    public List<PostComments> getCommentsProcess(@RequestParam int postId) {
        List<PostComments> outputListComments = getCommentsPost.getCommentProcess(postId);
        return outputListComments;
    }
    //update post (only title and description)
    @PutMapping("/updatePost")
    public postEntity updatePost(@RequestParam int postId, @RequestBody updatePostRequest updateData){
        //call the update service
        postEntity result = updateGate.updateProcess(postId,updateData);
        return result;
    }

    //TODO: create seperate requests for these --> insert new image, remove image, replace image
    //removes the content using content ID
    @DeleteMapping("/removeImage")
    public String removeImage(@RequestParam int contentID) {
        String result = removeTargetResource.removeTarget(contentID);
        return result;
    }
    //add a new image (limitations for 10 contents)
    @PostMapping("/addImage")
    public String addImage(@RequestParam int postID, @RequestParam("file") MultipartFile file) {
        boolean result2 = postCoAddUp.checkEligibility(postID);
        if (result2) {
            String result = postCoAddUp.addingProcess(file, postID);
            return result;
        }else{
            return "Maximum content amount exceeded";
        }
    }
    //TODO: create post

    //get the images (or contents reperately)
    @PostMapping("/saveAllContents")
    public String saveAllContents(@RequestParam("file")List<MultipartFile>file,@RequestParam int postID, @RequestParam int userID){ //saves to the content table
        if(file.size() > 10){
            throw new RuntimeException("Too many files (Max 10)");
        }
        file.forEach(files -> {
            if(!files.getContentType().equals("image/jpeg")){
                throw new RuntimeException("Invalid image type found within your images");
            }
        });
            //call the save function
        return "";
    }

    @PostMapping("/createPost")
    public int createProcess(@RequestParam int special,@RequestBody CreatePostRequest post){
        //to test
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("User not authenticated!");
        }
        /*
        * first save the basic contents
        * then save the images
        * --> send the kafka part
        * */
        int result = 0;
        //create the post object
        Users userIDobj = new Users();
        userIDobj.setUser_id(post.getUser_id());
        postEntity innerObj = new postEntity(post.getContent_type(),post.getDate(),post.getDescription(),post.getTime(),post.getTitle(),userIDobj);
        //validate
        if(post.getUser_id() <= 0){
            throw new RuntimeException("Invalid user ID");
        }else{
            //special -> if 1 wants to save contents or 0 not contents just send the message in here
            //call the save function (for the main table only)
            result = savePostProcess.savePostProcess(innerObj,special);
        }
        return result;
    }
}
