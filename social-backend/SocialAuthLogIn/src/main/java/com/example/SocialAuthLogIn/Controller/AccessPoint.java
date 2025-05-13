package com.example.SocialAuthLogIn.Controller;

import com.example.SocialAuthLogIn.Entity.users;
import com.example.SocialAuthLogIn.Services.checkUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@CrossOrigin(
//        origins = "http://localhost:5173",
//        allowedHeaders = "*",
//        allowCredentials = "true"
//)
@RequiredArgsConstructor
public class AccessPoint {
    @Autowired
    private checkUser ck;
    @CrossOrigin
    @GetMapping("/")
    public String getDetsils(OAuth2AuthenticationToken authentication){
        String fname = authentication.getPrincipal().getAttributes().get("given_name").toString();
        String lname = authentication.getPrincipal().getAttributes().get("family_name").toString();
        String picUrl = authentication.getPrincipal().getAttributes().get("picture").toString();
        String email = authentication.getPrincipal().getAttributes().get("email").toString();
//        return "Name : " + fname;
        Object obj1 = ck.checkUserDetailsByEmail(email);
        if(obj1 != null){
            return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Welcome</title>
                </head>
                <body style="margin: 0; padding: 0; background-color: #f0f2f5; font-family: 'Arial', sans-serif;">
                    <div style="max-width: 600px; margin: 50px auto; padding: 40px; background: white; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
                        <div style="text-align: center;">
                            <h1 style="color: #1877f2; margin: 20px 0 10px 0;">
                                Welcome, %s
                            </h1>
                            <p style="color: #606770; font-size: 18px; margin-bottom: 25px;">
                                Account successfully verified
                            </p>
                            <div style="background-color: #1877f2; padding: 12px 24px; border-radius: 6px; display: inline-block;">
                                <a href="http://localhost:5173/mainMenuTo" 
                                   style="color: white; text-decoration: none; font-weight: 500;">
                                    Continue to Dashboard
                                </a>
                            </div>
                            <p style="margin-top: 30px; color: #65676b;">
                                Logged in as: <span style="font-weight: 500;">%s</span>
                            </p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(fname, email);
        }else{
            return "Account not found";
        }
    }
}
