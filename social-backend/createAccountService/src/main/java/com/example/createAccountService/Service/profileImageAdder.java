package com.example.createAccountService.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class profileImageAdder {
    private final BlobServiceClient blobServiceClient;

    public profileImageAdder() {
        // Use the correct Azurite connection string with the full development storage account key
        String connectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;";

        this.blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    public String addImage(MultipartFile file, int fileNumber) throws IOException {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("profileimages");
        if (!containerClient.exists()) {
            containerClient.create();
        }
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String newFileName = "userpic" + fileNumber + ext;
        String newFileName = "userpic" + fileNumber + ".jpg";
        BlobClient blobClient = containerClient.getBlobClient(newFileName);
        // Upload the file
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        return blobClient.getBlobUrl();
    }
}
