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
            if(response.status === 200) {
                return response.data;
            }else{
                return response.status.toString;
            }
          })
          .catch(error => {
            return 'Error occured : ' + error;
          });
    }
}
export default removePost;