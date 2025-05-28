/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import axios from 'axios';
const createPostCont = (title,des,imagesArr,userID) => {
    const now = new Date();
    const dateGot = now.toISOString().split('T')[0]; 
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    const milliseconds = String(now.getMilliseconds()).padStart(3, '0');
    const timeGot = `${hours}:${minutes}:${seconds}.${milliseconds}000`; 
  
    if(title == "" || des == ""){
    alert('Title and description should not be empty');
  }else{
    if(imagesArr == null || imagesArr == ""){
      //sends 0
        return axios.post('http://localhost:8020/api/userAccount/createPost?special=0',{
          content_type: "TEXT",
          date: dateGot,
          description: des,
          time: timeGot,
          title: title,
          user_id: userID
        },{ withCredentials: true })
        .then(async response=>{
            if(response.status === 200){
               if(response.data != "" || response.data != null){
                alert('Post created');
              }
            }else if(response.status === 409){
              alert('Error occured when saving data (409) : createPostCont.jsx \n' + response.data);
            }else if(response.status === 500){
              alert('Internal server error (500): createPostCont.jsx');
            }
        })
        .catch(error=>{
          alert('Error occured when sending the request : createPostCont.jsx \n' + error);
        });
    }else{
      //sends 1
        var postID;
        var status = 0;
        axios.post('http://localhost:8020/api/userAccount/createPost?special=1',{
          content_type: "IMAGE",
          date: dateGot,
          description: des,
          time: timeGot,
          title: title,
          user_id: userID
        },{ withCredentials: true })
        .then(async response=>{
            if(response.status === 200){
              if(response.data != "" || response.data != null){
                postID = response.data;
                status = 1;
                //save images
                 const formData = new FormData();
      imagesArr.forEach((imageHolder)=>{
        formData.append('file',imageHolder.file);
      });
      console.log(formData);//FIXME:TEST
       axios.post('http://localhost:8020/api/userAccount/saveAllContents?postID='+postID+'&userID='+userID,
        formData,
        {
          withCredentials: true,
          headers: {
           'Content-Type': 'multipart/form-data'
          }
        }    
      )
        .then(async response=>{
            if(response.status === 200){
              if(response.data != "" || response.data != null){
                alert('Post created');
              }
            }else if(response.status === 409){
              alert('Error occured when saving data (409) : createPostCont.jsx \n' + response.data);
            }else if(response.status === 500){
              alert('Internal server error (500): createPostCont.jsx');
            }
        })
        .catch(error=>{
          alert('Error occured when sending the request : createPostCont.jsx \n' + error);
        });
                //save images
              }
            }else if(response.status === 409){
              status = 2;
              alert('Error occured when saving data (409) : createPostCont.jsx \n' + response.data);
            }else if(response.status === 500){
              status = 3;
              alert('Internal server error (500): createPostCont.jsx');
            }
        })
      //TODO: send the contents
      if(status == 1){
       
      }
    }
  }
}
export default createPostCont;