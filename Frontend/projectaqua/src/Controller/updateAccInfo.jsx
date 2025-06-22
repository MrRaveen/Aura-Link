/* eslint-disable no-empty */
 /* eslint-disable no-unused-vars */
import axios from 'axios';
import getIDprocess from '../Controller/getTheuserID';
class UpdateAccInfo{
    
    constructor(userIDIn,first_nameIn,last_nameIn,bioIn,birth_dateIn,mobileIn,addressIn,jobIn,choiceIn,userNameIn,passwordIn){
        this.userIDIn = userIDIn;
        this.first_nameIn = first_nameIn;
        this.last_nameIn = last_nameIn;
        this.bioIn = bioIn;
        this.birth_dateIn = birth_dateIn;
        this.mobileIn = mobileIn;
        this.addressIn = addressIn;
        this.jobIn = jobIn;
        this.choiceIn = choiceIn;
        this.userNameIn = userNameIn;
        this.passwordIn = passwordIn;
    }
    updateProcess(){
        //send the request
        return axios.put('http://localhost:8020/api/userAccount/updateProfile',{
            userID: this.userIDIn,
            first_name: this.first_nameIn,
            last_name: this.last_nameIn,
            bio: this.bioIn,
            birth_date: this.birth_dateIn,
            mobile: this.mobileIn,
            address: this.addressIn,
            job: this.jobIn,
            userName: this.userNameIn,
            password: this.passwordIn,
            choice: this.choiceIn
        }, { withCredentials: true })
        .then(async response=>{
            return response.data;
        })
        .catch(error=>{
            return "Error occured (updateAccInfo.jsx) : " + error;
        });
    }
    checkVerification(verificationCode){
         //send the request
        return axios.put('http://localhost:8020/api/userAccount/checkVerification?verificationCode='+verificationCode,{
            userID: this.userIDIn,
            first_name: this.first_nameIn,
            last_name: this.last_nameIn,
            bio: this.bioIn,
            birth_date: this.birth_dateIn,
            mobile: this.mobileIn,
            address: this.addressIn,
            job: this.jobIn,
            userName: this.userNameIn,
            password: this.passwordIn,
            choice: this.choiceIn
        }, { withCredentials: true })
        .then(async response=>{
            return response.data;
        })
        .catch(error=>{
            return "Error occured (updateAccInfo.jsx) : " + error;
        });
    }
    static updateProfileImg(userID,newImage){
        const formData = new FormData();
        formData.append('file',newImage);
          //send the request
        return axios.put('http://localhost:8020/api/userAccount/updateProfileImage?userID='+userID,formData,{
             withCredentials: true,
             headers: {
                'Content-Type': 'multipart/form-data'
             } 
        })
        .then(async response=>{
            return response.data;
        })
        .catch(error=>{
            return "Error occured when updating profile image (updateAccInfo.jsx) : " + error;
        });
    }
}
export default UpdateAccInfo;