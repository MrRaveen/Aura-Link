package com.example.UserProfile.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.UserProfile.entity.PostContentEntity;
import com.example.UserProfile.entity.PostContents;
import com.example.UserProfile.repository.PostContentRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RemoveTargetResource {
    //removes the target resource
    /*
    * get the content using the ID
    * remove the content
    * get the content name
    * remove the content from the azure
    * */
    @Autowired
    private PostContentRepo postContentRepo;

    @Autowired
    private BlobServiceClient blobServiceClient;

    @Transactional
    public String removeTarget(@RequestParam int contentID) {
        AtomicReference<String> contentName = new AtomicReference<>("");
        try{
            Optional<PostContentEntity>obtainedData = postContentRepo.findById(contentID);
            //check the content
            if (obtainedData.isPresent()) {
                obtainedData.ifPresent(PostContentEntity -> {
                    contentName.set(PostContentEntity.getMedia_name());
                });
                //remove content from table
                postContentRepo.deleteById(contentID);
                //remove from the blob
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("postcontents");
                boolean result = containerClient.getBlobClient(contentName.get()).deleteIfExists();
                if(result == true){
                    return "Removed content";
                }else{
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //rollback
                    return "Blob did not removed";
                }
            }else{
                return "No such post content";
            }
        }catch(Exception e){
            throw new RuntimeException("Error removing post content : " + e.toString());
        }
    }
}