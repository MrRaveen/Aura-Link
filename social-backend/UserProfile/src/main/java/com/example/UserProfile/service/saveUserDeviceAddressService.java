package com.example.UserProfile.service;

import com.example.UserProfile.entity.userDeviceAddress;
import com.example.UserProfile.repository.userDeviceAddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class saveUserDeviceAddressService {
    @Autowired
    private userDeviceAddressRepo addressObjMongo;
    public String saveUserDeviceAddress(userDeviceAddress sendingObj) throws Exception {
        try{
            //chack weather there is an existing address
            Optional<userDeviceAddress> result1 = addressObjMongo.findById(sendingObj.getId());
            if(result1.isPresent()) {
                userDeviceAddress clsOut = result1.get();
                if(clsOut.getAddressLong() != sendingObj.getAddressLong()){
                    //update address
                    addressObjMongo.deleteById(sendingObj.getId());
                    userDeviceAddress outSavedForUpdate = addressObjMongo.save(sendingObj);
                    return outSavedForUpdate.getAddressLong();
                }else{
                    return clsOut.getAddressLong();
                }
            }else{
                userDeviceAddress outSaved = addressObjMongo.save(sendingObj);
                return outSaved.getAddressLong();
            }
        }catch(Exception e){
            throw new Exception("Error saving user device address : " + e.getMessage());
        }
    }
}
