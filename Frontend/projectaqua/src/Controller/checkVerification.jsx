/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import axios from 'axios';
class CheckVerification{
    checkVerificationFun(tempDataID,verificationID){
        return axios.get('http://localhost:8070/api/create/checkVerification?tempDataID='+tempDataID+'&verificationID='+verificationID)
            .then(response => {
              if(response.status === 200) {
                  const newUserIDmain = response.data;
                  //console.log("Passed :: " + newUserIDmain); //FIXME: TEST OUT
                  return newUserIDmain;
              }else{
                  return null;
              }
            })
            .catch(error => {
              return 'Error occured';
            });
      }
     removeTempDataByID(tempDataID){
      return axios.delete('http://localhost:8070/api/create/removeTempDataErr?tempDataID='+tempDataID)
      .then(response => {
        if(response.status === 200) {
          if(response.data == 1){
            console.log('REMOVED'); //FIXME: TO TEST
            return 'Removed';
          }else{
            console.log('NOT REMOVED'); //FIXME: TO TEST
            return 'not Removed';
          }
            
        }else{
            return null;
        }
      })
      .catch(error => {
        console.log('Error in the CheckVerification class :: ' + error);
        return 'Error occured :: ' + error;
      });
     } 
     addProfileImage(profileImage, fileNumber){
      const formData = new FormData();
      formData.append('file',profileImage);
      return axios.post('http://localhost:8070/api/create/saveProfileImage?fileNumber='+fileNumber,formData)
      .then(response => {
        if(response.status === 200) {
          if(response.data != 'Error occured'){
            console.log('Profile image added :: ' + response.data); //FIXME: TO TEST --> link to picture
            return 'Added';
          }else{
            console.log('Profile image is not added :: ' + response.data); //FIXME: TO TEST
            return 'Not added';
          }
            
        }else{
            return null;
        }
      })
      .catch(error => {
        console.log('Error in the CheckVerification class :: ' + error);
        return 'Error occured :: ' + error;
      });
     }
}
export default CheckVerification;