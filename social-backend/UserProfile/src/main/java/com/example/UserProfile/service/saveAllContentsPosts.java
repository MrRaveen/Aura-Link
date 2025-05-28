package com.example.UserProfile.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.UserProfile.entity.PostContentEntity;
import com.example.UserProfile.entity.postEntity;
import com.example.UserProfile.repository.PostContentRepo;
import com.example.UserProfile.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class saveAllContentsPosts {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private PostContentRepo postContentRepo;
    //kafka part
    private static final String TOPIC = "my_topic";
    @Autowired
    private KafkaTemplate<String, postEntity> kafkaTemplate;

    /*
    * 1. get the post by ID
    * 2. loop the contents and store in the cloud
    * 3. send the kafka message using post object
    * */
    @Autowired
    private BlobServiceClient blobServiceClient;

    public void saveProcess(List<MultipartFile> files, int postID, int userID) throws Exception {
       try{
           //get the post by ID
           postEntity requiredPost = postRepo.findById(postID).get();
           //save contents to blob
           BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("postcontents");
           if (!containerClient.exists()) {
               containerClient.create();
           }
           files.forEach(file -> {
               //save the contents to the table
               //meta data
               String fileName = file.getOriginalFilename();//get the name
               String type = file.getContentType(); //get the file type
               long sizeMeta = file.getSize();
               sizeMeta = sizeMeta / 1000;
               String metaData = "{\"size\":" +"\""+ String.valueOf(sizeMeta) +"\""+ ",\"fileName\":" +"\""+ fileName +"\""+ "}";
               //for the postID
               postEntity postIDobj = new postEntity();
               postIDobj.setPostid(postID);
               PostContentEntity passingData = new PostContentEntity(fileName,metaData,type,postIDobj);
               PostContentEntity createdContent = postContentRepo.save(passingData);
               //create the new file name
               int newContentID = createdContent.getContentid();
               String newFileName = "CONT"+newContentID+".jpg";
               passingData.setMedia_name(newFileName);
               PostContentEntity updatedContent = postContentRepo.save(passingData);//update the data with the new file name
               //save to azure
               BlobClient blobClient = containerClient.getBlobClient(newFileName);
               // Upload the file
               try {
                   blobClient.upload(file.getInputStream(), file.getSize(), true);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           });
           //TODO: send the kafka message
           kafkaTemplate.send(TOPIC, requiredPost);
           System.out.println("Message sent from content save");//FIXME:TEST
       }catch(Exception e){
           throw new Exception("Error occured when saving contents : " + e.toString());
       }

    }
}
