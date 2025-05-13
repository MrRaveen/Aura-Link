package com.example.createAccountService.Service;

import com.example.createAccountService.Entity.user_profiles;
import com.example.createAccountService.Entity.user_profilesMongo;
import com.example.createAccountService.Entity.users;
import com.example.createAccountService.Entity.usersMongo;
import com.example.createAccountService.Repo.user_profilesRepo;
import com.example.createAccountService.Repo.user_profilesRepoMongo;
import com.example.createAccountService.Repo.usersRepo;
import com.example.createAccountService.Repo.usersRepoMongo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class checkVerification {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private usersRepoMongo urm1;
    @Autowired
    private user_profilesRepoMongo upm1;
    @Autowired
    private usersRepo userRepository;
    @Autowired
    private user_profilesRepo user_profileRepository;
    public int checkVerificationFun(String tempDataID,String verificationID){
        Optional<usersMongo> requiredTempData = urm1.findById(tempDataID); //may or may not contain a value
        List<user_profilesMongo> requiredTempProfileDataList = upm1.findAll();
        List<user_profilesMongo>requiredProfileData = new ArrayList<>();
        if(requiredTempData.isPresent()){
            usersMongo user = requiredTempData.get();
            String codeHolder = user.getVerificationCode();
            System.out.println(user.getVerificationCode());
            if(codeHolder.equals(verificationID)){
                users newUser = new users(user.getUsername(),user.getEmail(),user.getPassword_hash(),user.getCreated_at());
                System.out.println("new user " + newUser);
                System.out.println("new profile "+requiredProfileData); //test
                users createdUser = userRepository.save(newUser);
                urm1.deleteById(tempDataID);
                requiredTempProfileDataList.forEach((p)->{
                    if(p.getUser_id().equals(tempDataID)){
                        user_profiles newProfile = new user_profiles(newUser,p.getFirst_name(),p.getLast_name(),
                                p.getBio(),p.getProfile_pic_url(),p.getLatLocation(),p.getLonLocation(),
                                p.getBirdth_date(),p.getJoined_at(),p.getAge(),p.getMobile(),p.getAddress(),
                                p.getJob());
                        newProfile.setProfile_pic_url("http://127.0.0.1:10000/devstoreaccount1/profileimages/userpic"+createdUser.getUser_id() + ".jpg");
                        user_profileRepository.save(newProfile);
                        upm1.deleteById(p.getId());
                    }
                });
                return createdUser.getUser_id();
            }else{
                return -1; //not equal
            }
        }else{
            return 0; //no tem data
        }
    }
}