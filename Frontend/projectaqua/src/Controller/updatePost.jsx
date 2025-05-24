/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import axios from 'axios';
class updatePost{
    constructor(postID){
        this.postID = postID;
    }
    updateProcess(ti,des){
       return axios.put('http://localhost:8020/api/userAccount/updatePost?postId='+this.postID,{
        title: ti,
        description: des
       })
          .then(response => {
            return response.data;
          })
          .catch(error => {
            return 'Error occured in (updatePost) : ' + error;
          });

    }
}
export default updatePost;