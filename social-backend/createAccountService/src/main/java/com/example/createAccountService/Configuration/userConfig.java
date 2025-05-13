package com.example.createAccountService.Configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.createAccountService.Service.checkVerification;
import com.example.createAccountService.Service.insertTempData;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class userConfig {
    @Bean
    public insertTempData logInBean() {
        return new insertTempData();
    }
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .endpoint("http://127.0.0.1:10000/devstoreaccount1")
                .credential(new com.azure.storage.common.StorageSharedKeyCredential(
                        "devstoreaccount1",
                        "Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw=="
                ))
                .buildClient();
    }
}
