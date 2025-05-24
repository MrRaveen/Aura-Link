/* eslint-disable no-unused-vars */
import axios from 'axios';
class imageAdd{
    constructor(imageFile,postID,userID){
        this.imageFile = imageFile;
        this.postID = postID;
        this.userID = userID;
    }
    handleImgInsert(){
    //get the userid
    const file = this.imageFile.current.files[0];
    console.log('img insert : ' + file.name + "\n" + this.postID);//FIXME: test
    const formData = new FormData();
    formData.append('file',file);
    //send the request
       return axios.post('http://localhost:8020/api/userAccount/addImage?postID='+ this.postID+'&userID='+this.userID,formData)
          .then(response => {
            if(response.status === 200) {
               return response.data;
            }else{
                return response.status.toString;
            }
          })
          .catch(error => {
            return 'Error occured in image send : '+ error;
          });
    }
}
export default imageAdd;