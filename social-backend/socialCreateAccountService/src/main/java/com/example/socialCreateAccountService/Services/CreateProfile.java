package com.example.socialCreateAccountService.Services;

import com.example.socialCreateAccountService.Entity.user_profiles;
import com.example.socialCreateAccountService.Entity.users;
import com.example.socialCreateAccountService.Repo.user_profilesRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreateProfile {
    @Autowired
    private user_profilesRepo usProRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional
    public Map<String, Object> createAccount(user_profiles us2Pro) throws Exception{
        Map<String, Object> result = new HashMap<>();
        if(us2Pro.getFirst_name() == null || us2Pro.getLast_name() == null || us2Pro.getProfile_pic_url() == null){
            result.put("Error","Email or Created time is empty");
            return result;
        }else{
            result.put("saved_profile",usProRepo.save(us2Pro));
            if(result.get("saved_profile") == null){
                result.clear();
                result.put("Error","Error occurred when saving user");
            }
            return result;
        }
    }
}
