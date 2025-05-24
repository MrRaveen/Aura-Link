/* eslint-disable no-empty */
 /* eslint-disable no-unused-vars */
import axios from 'axios';
import getIDprocess from '../Controller/getTheuserID';
class UserAccountPersonal{
    getAccInfo(){
        const userID = getIDprocess();
        //send the request
        return axios.get('http://localhost:8020/api/userAccount/getAllAccInfo?userId='+userID,{ withCredentials: true })
        .then(async response=>{
            if(response.status === 200){
                const data = await response.data
                return data; //FIXME: to test
            }else{
                return null;
            }
        })
        .catch(error=>{
            return "Error occured";
        });
    }
    getAllPosts(){
        const userID = getIDprocess();
          return axios.get('http://localhost:8020/api/userAccount/getAllPosts?userId='+userID)
        .then(async response=>{
            if(response.status === 200){
                const data = await response.data
                return data; //FIXME: to test
            }else{
                return null;
            }
        })
        .catch(error=>{
            return "Error occured";
        });
    }
}
export default UserAccountPersonal;