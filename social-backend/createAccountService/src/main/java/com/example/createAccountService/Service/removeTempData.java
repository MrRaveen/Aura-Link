package com.example.createAccountService.Service;

import com.example.createAccountService.Repo.user_profilesRepoMongo;
import com.example.createAccountService.Repo.usersRepoMongo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class removeTempData {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private usersRepoMongo urm1;
    @Autowired
    private user_profilesRepoMongo upm1;
    public String removeProcess(String tempDataID){
        try{
            urm1.deleteById(tempDataID);
            upm1.deleteById(tempDataID);
            return "Removed Successfully";
        }catch(Exception e){
            return "Error occured: "+e.getMessage();
        }
    }
}
