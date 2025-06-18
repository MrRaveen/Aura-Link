/* eslint-disable no-unused-vars */
import axios from 'axios';
class removePost{
    constructor(postID){
        this.postID = postID;
    }
    async processRemove(){
        console.log('removing!! : ' + this.postID);//FIXME: test
           return await axios.delete('http://localhost:8020/api/userAccount/removePost?postId='+this.postID)
          .then(response => {
             return response.data;
          })
          .catch(error => {
            return 'Error occured : ' + error;
          });
    }
}
export default removePost;