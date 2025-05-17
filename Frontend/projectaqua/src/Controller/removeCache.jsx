import axios from 'axios';
class removeCache{
    constructor(userID){
        this.userID = userID;
    }
    removeRequest(){
            return axios.delete('http://localhost:8020/api/userAccount/removeCache?userID='+this.userID)
          .then(response => {
            if(response.status === 200) {
               return response.data;
            }else{
                return response.status.toString;
            }
          })
          .catch(error => {
            return 'Error occured in redis remove cache : '+ error;
          });
    }
}
export default removeCache;