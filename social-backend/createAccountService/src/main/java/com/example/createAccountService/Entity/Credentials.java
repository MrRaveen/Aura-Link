package com.example.createAccountService.Entity;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Credentials {
    @Value("${mailjet.api.key}")  // Add these properties
    private String mailjetApiKey;

    @Value("${mailjet.api.secret}")
    private String mailjetApiSecret;
    private MailjetClient client = null;
    public MailjetClient getMailConnection() {
        if(client == null) {
            ClientOptions options = ClientOptions.builder()
                .apiKey(mailjetApiKey)
                .apiSecretKey(mailjetApiSecret)
                .build();
            MailjetClient client = new MailjetClient(options);
            return client;
        }
        return client;
    }

}
