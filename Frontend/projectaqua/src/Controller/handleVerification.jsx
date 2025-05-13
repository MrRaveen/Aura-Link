/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import axios from 'axios';
//latitudes and longitudes

// var lat;
// var lon;
// function showExactPosition(position) {
//     lat = position.coords.latitude;
//     lon = position.coords.longitude;
// }
function pad(num) {
  return num.toString().padStart(2, '0');
}
class handleVerificationProcess{
    constructor(userName,email){
        this.userName = userName;
        this.email = email;
    }
    checkEligibility(){
        //HTTP requests are asynchronous operations.
        //The method would return undefined immediately, before the HTTP request completes.
       return axios.get('http://localhost:8070/api/create/checkEligibility?userName='+this.userName+'&email='+this.email)
          .then(response => {
            if(response.status === 200) {
                const userNameEligibility = response.data.userName;
                const mailEligibility = response.data.email;
                console.log("Passed :: " + userNameEligibility);
                var reply = "";
                if(userNameEligibility == false){
                    reply = reply + "Username is being used\n";
                }
                if(mailEligibility == false){
                    reply = reply + "Email is being used";
                }
                return reply;
            }else{
                return response.status.toString;
            }
          })
          .catch(error => {
            return 'Error occured';
          });
    }
    saveDataTempToMongo(userName,email,password,fname,lname,bio,dob,age,mobile,address,job){
          var d = new Date(); 
          // if (navigator.geolocation) {
          //   navigator.geolocation.getCurrentPosition(showExactPosition);
          //   console.log("latitude : "+lat);
          //   console.log("longitude : " + lon);
          // } else {
          //   try{
          //       throw new Error('Geolocation is not supported');
          //   }catch(error){
          //       alert('Error : ' + error.message);
          //   }
          // }
          const timeString = `${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`;
        return axios.post('http://localhost:8070/api/create/addTempUserMongo',{
            user: {
                username: userName,
                email: email,
                password_hash: password,
                created_at: timeString
              },
              profile: {
                first_name: fname,
                last_name: lname,
                bio: bio,
                profile_pic_url: "http://127.0.0.1:10000/devstoreaccount1/profileimages/",
                latLocation: "0",
                lonLocation: "0",
                birdth_date: dob,
                joined_at: timeString,
                age: age,
                mobile: mobile,
                address: address,
                job: job
              }
        })
        .then(response => {
          if(response.status === 200) {
             var resVariable = response.data;
              return resVariable;
          }else{
              return null;
          }
        })
        .catch(error => {
          return 'Error occured : ' + error;
        });
    }
}
export default handleVerificationProcess;