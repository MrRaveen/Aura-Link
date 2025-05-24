package com.example.createAccountService.Service;

import com.example.createAccountService.Entity.Credentials;
import com.example.createAccountService.Entity.user_profilesMongo;
import com.example.createAccountService.Entity.usersMongo;
import com.example.createAccountService.Repo.user_profilesRepoMongo;
import com.example.createAccountService.Repo.usersRepoMongo;
import com.example.createAccountService.Responses.userMongoResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.*;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;

@RequiredArgsConstructor
public class insertTempData {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private usersRepoMongo urm1;
    @Autowired
    private user_profilesRepoMongo upm1;
    @Autowired
    private Credentials cre1;
    public userMongoResponse saveUserTemp(usersMongo user, user_profilesMongo user_profile) throws MailjetException {
        try{
            usersMongo savedData = urm1.save(user);
            user_profile.setUser_id(savedData.getId());
            try{
                user_profilesMongo savedData2 = upm1.save(user_profile);
            }catch(Exception ll){
                urm1.deleteById(savedData.getId());
                userMongoResponse exceptionUseru5 = new userMongoResponse();
                return exceptionUseru5;
            }
            //create random number
            SecureRandom secureRandom = new SecureRandom();
            int randomNumber = secureRandom.nextInt(100);
            MailjetClient client = cre1.getMailConnection();
            TransactionalEmail message1 = TransactionalEmail
                    .builder()
                    .to(new SendContact(savedData.getEmail(), "stanislav"))
                    .from(new SendContact("saliyaautocare52@gmail.com", "Mailjet integration test"))
                    .htmlPart("<h1>Your verification code : " + randomNumber + "</h1>")
                    .subject("This is the subject")
                    .trackOpens(TrackOpens.ENABLED)
                    .header("test-header-key", "test-value")
                    .customID("custom-id-value")
                    .build();
            SendEmailsRequest request = SendEmailsRequest
                    .builder()
                    .message(message1) // you can add up to 50 messages per request
                    .build();
            //SendEmailsResponse response = request.sendWith(client);
            //update data
            user.setVerificationCode(String.valueOf(randomNumber));
            urm1.save(user); //update the verification code
            userMongoResponse u1 = new userMongoResponse(savedData.getId(),savedData.getUsername(),savedData.getEmail(),savedData.getPassword_hash(),savedData.getCreated_at(),user.getVerificationCode());
            return u1;
        }catch(Exception ee){
            //String id,String username, String email, String password_hash, LocalTime created_at, String verificationCode
            userMongoResponse exceptionUseru5 = new userMongoResponse();
            return exceptionUseru5;
        }
    }
}
