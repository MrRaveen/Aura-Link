package com.example.createAccountService.Service;

import com.example.createAccountService.Entity.Interests;
import com.example.createAccountService.Entity.users;
import com.example.createAccountService.Repo.InterestsRepo;
import com.example.createAccountService.Repo.usersRepo;
import com.example.createAccountService.Requests.Interest;
import com.example.createAccountService.Requests.InterestRequestList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveAllRequests {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private InterestsRepo repo;
    @Autowired
    private usersRepo userRepo;
    public String saveAllRequests(List<Interest> allRequests){
        try{
            allRequests.forEach(interest -> {
                Optional<users>userOptional  = userRepo.findById(interest.getUser_id());
                if(userOptional.isPresent()){
                    users getUser = userOptional.get();
                    //users u1 = new users(getUser.getUsername(),getUser.getEmail(),getUser.getPassword_hash(),getUser.getCreated_at());
                    Interests saveInterst = new Interests(getUser,interest.getName(),interest.getCategory());
                    Interests savedObj = repo.save(saveInterst);
                    //System.out.println("OUT VALUES :: " + savedObj.getCategory() + " " + savedObj.getUser_id());
                }
            });
            return "Saved all requests";
        }catch(Exception e){
            return "Error saving all requests: " + e.getMessage();
        }
    }
}
