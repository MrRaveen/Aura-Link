package com.example.socialCreateAccountService.Services;
import com.example.socialCreateAccountService.Entity.user_profiles;
import com.example.socialCreateAccountService.Entity.users;
import com.example.socialCreateAccountService.Repo.user_profilesRepo;
import com.example.socialCreateAccountService.Repo.usersRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//@Service
public class CreateAccount {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private usersRepo repository;
//    @Autowired
//    private user_profilesRepo usProRepo;
    @Transactional
    public Map<String, Object> createAccount(users us1) throws Exception{
        Map<String, Object> result = new HashMap<>();
        if(us1.getEmail().isEmpty() || us1.getCreated_at() == null){
            result.put("Error","Email or Created time is empty");
            return result;
        }else{
            result.put("saved_user",repository.save(us1));
//            result.put("saved_profile",usProRepo.save(us2Pro));
            if(result.get("saved_user") == null) {
                result.clear();
                result.put("Error","Error occurred when saving user");
            }
            return result;
        }
    }
}
