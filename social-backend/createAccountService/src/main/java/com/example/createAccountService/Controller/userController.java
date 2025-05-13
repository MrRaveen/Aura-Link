package com.example.createAccountService.Controller;

import com.example.createAccountService.Entity.user_profilesMongo;
import com.example.createAccountService.Entity.usersMongo;
import com.example.createAccountService.Requests.*;
import com.example.createAccountService.Responses.EligibilityResponse;
import com.example.createAccountService.Responses.userMongoResponse;
import com.example.createAccountService.Service.*;
import com.mailjet.client.errors.MailjetException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/create")
public class userController {
    @Autowired
    private insertTempData it1;
    @Autowired
    private checkVerification verificationObj;
    @Autowired
    private removeTempData remover;
    @Autowired
    private SaveAllRequests saveAll;
    @Autowired
    private profileImageAdder addImage;
    @Autowired
    private checkValidity checkValidity;
    @PostMapping("/addTempUserMongo")
    public userMongoResponse addTempUserDetails(@RequestBody UserTempRequest request) throws MailjetException {
        try{
            usersMongo users1 = new usersMongo(
                    request.getUser().getUsername(),
                    request.getUser().getEmail(),
                    request.getUser().getPassword_hash(),
                    request.getUser().getCreated_at(),
                    ""
            );

            user_profilesMongo users_profile1 = new user_profilesMongo(
                    request.getProfile().getFirst_name(),
                    request.getProfile().getLast_name(),
                    request.getProfile().getBio(),
                    request.getProfile().getProfile_pic_url(),
                    request.getProfile().getLatLocation(),
                    request.getProfile().getLonLocation(),
                    request.getProfile().getBirdth_date(),
                    request.getProfile().getJoined_at(),
                    request.getProfile().getAge(),
                    request.getProfile().getMobile(),
                    request.getProfile().getAddress(),
                    request.getProfile().getJob(),""
            );
            usersMongo us1 = new usersMongo(users1.getUsername(),users1.getEmail(),users1.getPassword_hash(),users1.getCreated_at(),"");
            user_profilesMongo us21 = new user_profilesMongo(users_profile1.getFirst_name(),users_profile1.getLast_name(),users_profile1.getBio(),users_profile1.getProfile_pic_url(),users_profile1.getLatLocation(),users_profile1.getLonLocation(),users_profile1.getBirdth_date(),users_profile1.getJoined_at(),users_profile1.getAge(),users_profile1.getMobile(),users_profile1.getAddress(),users_profile1.getJob(),"");
            userMongoResponse us2 = it1.saveUserTemp(us1,us21);
            return us2;
        }catch(Exception e){
            userMongoResponse exceptionUseru5 = new userMongoResponse();
            return exceptionUseru5;
        }
    }
    @GetMapping("/checkVerification")
    public int checkVerification(@RequestParam String tempDataID, @RequestParam String verificationID){
        if(tempDataID != null && verificationID != null){
            int status = verificationObj.checkVerificationFun(tempDataID,verificationID);
            return status;
        }else{
            return -2;
        }
    }
    @DeleteMapping("/removeTempDataErr")
    public int removeTempData(@RequestParam String tempDataID){
        String status = remover.removeProcess(tempDataID);
        System.out.println(status);
        if(status.equals("Removed Successfully")){
            return 1;
        }else{
            return 0;
        }
    }
    @PostMapping("/addAllRequests")
    public String addRequests(@RequestBody InterestRequestList allObj){
        List<Interest> allRequests = allObj.getAllRequests();
        //System.out.println(allRequests);
        String status = saveAll.saveAllRequests(allRequests);
        return status;
    }
    @PostMapping("/saveProfileImage")
    public String addProfileImage(@RequestParam("file") MultipartFile file,@RequestParam int fileNumber){
        try{
         String url = addImage.addImage(file,fileNumber);
         return url;
        }catch(Exception e){
            System.out.println(e.toString());
            return "Error occured";
        }
//        return "passed";
    }

    @GetMapping("/checkEligibility")
    public EligibilityResponse checkProcess(@RequestParam String userName, @RequestParam String email){
        Object userNameResult = checkValidity.checkValidityUserName(userName);
        Object emailResult = checkValidity.checkValidityMail(email);
        boolean userNameBool = false;
        boolean emailBool = false;
        if(userNameResult == null){
            userNameBool = true;
        }
        if(emailResult == null){
            emailBool = true;
        }
        EligibilityResponse elgObj1 = new EligibilityResponse(userNameBool,emailBool);
        return elgObj1;
    }
}