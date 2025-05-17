/* eslint-disable no-unused-vars */
import axios from 'axios';
class removeImage{
    constructor(contentID){
        this.contentID = contentID;
    }
    removeImgProcess(){
        //send remove request
          return axios.delete('http://localhost:8020/api/userAccount/removeImage?contentID='+ this.contentID)
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
export default removeImage;