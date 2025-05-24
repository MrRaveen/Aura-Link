/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import axios from 'axios';
class HandleInterestSend{
    constructor(userID,name,category){
        this.userID = userID;
        this.name = name;
        this.category = category;
    }
    addingProcess(){
        return axios.post('http://localhost:8070/api/create/addAllRequests',{
            allRequests : [
                {
                    user_id: this.userID,
                    name: this.name,
                    category: this.category
                }
            ]
        })
        .then(response => {
          if(response.status === 200) {
              if(response.data === 'Saved all requests'){
                return 'Entered';
              }else{
                return 'Not entered';
              }
          }else{
              return response.status.toString;
          }
        })
        .catch(error => {
          console.log('ERROR IN INTEREST HANDLE (EXCEPTION) : ' + error);
          return 'Error occured';
        });
    }
}
export default HandleInterestSend;