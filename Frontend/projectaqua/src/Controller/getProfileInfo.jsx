import axios from 'axios';
const GetProfileInfo = (userIDIn) => {
     //send the request
        return axios.get('http://localhost:8020/api/userAccount/getProfileInfoUser?userID='+userIDIn,{ withCredentials: true })
        .then(async response=>{
            return response.data;
        })
        .catch(error=>{
            return "Error occured (updateAccInfo.jsx) : " + error;
        });
}
export default GetProfileInfo;