const express = require('express');
const app = express();
app.use(express.json());
//querry types
const { QueryTypes, where } = require('sequelize');
//DB Connection
const sequelize = require('./dbConn');
//model imports
const userProfileImport = require('./Models/userProfile');
const userImport = require('./Models/user');
const postsImport = require('./Models/posts');
const follower_infoImport = require('./Models/follower_info');
const post_contentImport = require('./Models/post_content');
const post_reactionImport = require('./Models/post_reaction');
const post_commentsImport = require('./Models/post_comments');

//FIXME: Test section
const postResponseImport = require('./postResponse');
//test section

//user profile table
const userProfileObj = new userProfileImport();
const userProfile = userProfileObj.getDataModel();
//user table
const userObj = new userImport();
const user = userObj.getDataModel();
//post table
const postsObj = new postsImport();
const posts = postsObj.getDataModel();
//follower_info table
const follower_infoObj = new follower_infoImport();
const follower_info = follower_infoObj.getDataModel();
//post_content table
const post_contentObj = new post_contentImport();
const post_content = post_contentObj.getDataModel();
//post_reaction table
const post_reactionObj = new post_reactionImport();
const post_reaction = post_reactionObj.getDataModel();
//post_comments table
const post_commentsObj = new post_commentsImport();
const post_comments = post_commentsObj.getDataModel();
//DB conn establishment
sequelize.authenticate().then(() => {
    console.log('Connection has been established successfully.');
}).catch((error) => {
    console.error('Unable to connect to the database: ', error);
 });
 sequelize.sync().then(() => {
    console.log('users table created successfully!');
 }).catch((error) => {
    console.error('Unable to create table : ', error);
 });  
 
 //endpoints
app.get('/custom1/:email',(req,res)=>{
     sequelize.sync().then(() => {
        user.findOne({
            where: {
                email : req.params.email
            }
        }).then(dataGot => {
            if(dataGot == null){
                console.log('User not found');
                res.send(null);
            }else{
                console.log(dataGot);
                res.send(dataGot);
            }
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
        });
    
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });
});

app.get('/checkMail/:email',(req,res)=>{
     sequelize.sync().then(() => {
        user.findOne({
            where: {
                email : req.params.email
            }
        }).then(dataGot => {
            if(dataGot == null){
                console.log('User not found');
                res.send(null);
            }else{
                console.log(dataGot);
                res.send(dataGot);
            }
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
        });
    
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });
});


app.get('/checkUserName/:userName',(req,res)=>{ 
     sequelize.sync().then(() => {
        user.findOne({
            where: {
                username : req.params.userName
            }
        }).then(dataGot => {
            if(dataGot == null){
                console.log('User not found');
                res.send(null);
            }else{
                console.log(dataGot.dataValues);
                res.send(dataGot.dataValues);
            }
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
        });
    
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });
});
//get profile data
app.get('/getProfileData/:userID',(req,res)=>{
     //fetch the data
     sequelize.sync().then(() => {
        userProfile.findOne({
            where: {
                user_id : req.params.userID
            }
        }).then(dataGot => {
            if(dataGot == null){
                console.log('User not found');
                res.send(null);
            }else{
                console.log(dataGot);
                res.send(dataGot);
            }
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
        });
    
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });

});


//get follwer the count (count related to followers followed by the user)
app.get('/getFollowCount/:userID', (req,res)=>{
    sequelize.sync().then(() => {
        follower_info.count({
            where: {
                user_id : req.params.userID
            }
        }).then(dataGotFollowers => {
            const jsonOutput = {
                followCount : dataGotFollowers.toString()
            }
            const jsonContent = JSON.stringify(jsonOutput);
            res.send(jsonContent);
        }).catch((error)=>{
            console.error('Failed to get data IN follwers part : ',error);
        });
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });
});
//get the post count
app.get('/getPostCount/:userID', (req,res)=>{
    sequelize.sync().then(() => {
        //post count
        posts.count({
            where: {
                user_id : req.params.userID
            }
        }).then(dataGot => {
            const jsonOutput = {
                count : dataGot.toString()
            }
            const jsonContent = JSON.stringify(jsonOutput);
            res.send(jsonContent);
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
        });
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });
});
//get the following count
app.get('/getFollowingCount/:userID', (req,res)=>{
    sequelize.sync().then(() => {
        follower_info.count({
            where: {
                follower : req.params.userID
            }
        }).then(dataGot => {
            const jsonOutput = {
                followerscountNumber : dataGot.toString()
            }
            const jsonContent = JSON.stringify(jsonOutput);
            res.send(jsonContent);
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
        });
    }).catch((error) => {
        console.error('Unable to create table : ', error);
    });
});
//get all posts
app.get('/getAllPosts/:userID',async (req,res)=>{
    const AllPostHolder =  await posts.findAll({ where: { user_id : req.params.userID } });//all posts
    var PostContentHolder = []; //stores post content for all posts
    var PostReactionHolder = [];//stores the reactions for all posts
    for(let count = 0;count<AllPostHolder.length;count++){
        var PostContentTemp = await post_content.findAll({ where: {postid: AllPostHolder[count].postid}});//stores the post content of each post (single)
        
        var reactionTemLAUGH = await post_reaction.count({ where: {postid: AllPostHolder[count].postid,react_type: 'LAUGH'}});
        var reactionTemLIKE = await post_reaction.count({ where: {postid: AllPostHolder[count].postid,react_type: 'LIKE'}});
        var reactionTemLOVE = await post_reaction.count({ where: {postid: AllPostHolder[count].postid,react_type: 'LOVE'}});
        var reactionTemSAD = await post_reaction.count({ where: {postid: AllPostHolder[count].postid,react_type: 'SAD'}});
        const jsonOutput = {
            laugh: reactionTemLAUGH,
            like: reactionTemLIKE,
            love: reactionTemLOVE,
            sad: reactionTemSAD
        }
        PostReactionHolder.push(jsonOutput);//stores the reactions of all posts
        PostContentHolder.push(PostContentTemp);//post contents for all post
    }
    const testObj = new postResponseImport(AllPostHolder,PostContentHolder,PostReactionHolder);//combines the posts , post content and reactions
    res.send(testObj);
});
//get the post comments
app.get('/getPostComments/:postID',async (req,res)=>{
    const commentsOut = await sequelize.query(
        'SELECT post_comments.commentid, post_comments.comment, post_comments.postid, post_comments.user_id, user_profiles.first_name, user_profiles.last_name FROM `post_comments` INNER JOIN `user_profiles` ON post_comments.user_id = user_profiles.user_id WHERE post_comments.postid = ' + req.params.postID + ";",
        {
          type: QueryTypes.SELECT,
        }
      );
      res.send(commentsOut);
});
app.get('/postContentCount/:postID',(req,res)=>{
      sequelize.sync().then(() => {
        post_content.count({
            where: {
                postid : req.params.postID
            }
        }).then(dataGot => {
            res.send(dataGot.toString());
        }).catch((error)=>{
            console.error('Failed to get data : ',error);
            res.send(error);
        });
    }).catch((error) => {
        console.error('Unable to create table : ', error);
        res.send(error);
    });
});
app.get('/getFollowsers/:userID',async (req,res)=>{
    const result = await sequelize.query('SELECT follower FROM follower_info WHERE user_id = ' + req.params.userID +';',{
        type: QueryTypes.SELECT,
    });
    res.send(result);
});
//post remove process
//remove comments
app.delete('/removeComments/:postID',(req,res)=>{
    sequelize.sync().then(()=>{
        post_comments.destroy({
            where: {
                postid : req.params.postID
            }
        }).then(response => {
            res.send(response.toString());
        }).catch((error)=>{
            res.send('Error occured when removing data : ' + error);
        });
    }).catch((error)=>{
         res.send('Error occured when removing data : ' + error);
    });
});
//remove the post reactions
app.delete('/removeReactions/:postID',(req,res)=>{
    sequelize.sync().then(()=>{
        post_reaction.destroy({
            where: {
                postid : req.params.postID
            }
        }).then(response => {
            res.send(response.toString());
        }).catch((error)=>{
            res.send('Error occured when removing data : ' + error);
        });
    }).catch((error)=>{
         res.send('Error occured when removing data : ' + error);
    });
});
//get the post content names
app.get('/getPostContentName/:postID',(req,res)=>{
    sequelize.sync().then(()=>{
        post_content.findAll({
            where: {
                postid : req.params.postID
            }
        }).then(response => {
            res.send(response);
        }).catch((error)=>{
            res.send('Error occured when removing data : ' + error);
        });
    }).catch((error)=>{
         res.send('Error occured when removing data : ' + error);
    });
});
//remove from content table
app.delete('/removePostDetailsAll/:postID',(req,res)=>{
    sequelize.sync().then(()=>{
        post_content.destroy({
            where: {
                postid : req.params.postID
            }
        }).then(response => {
            const jsonRes = {
                message: "Removed",
                ststus: 200
            }
            res.send(jsonRes);
        }).catch((error)=>{
            const jsonRes = {
                message: "Error occured",
                ststus: 200
            }
            res.send(jsonRes);
        });
    }).catch((error)=>{
            const jsonRes = {
                message: "Error occured",
                ststus: 200
            }
            res.send(jsonRes);
    });
});
app.put('/updateProfile/',async (req,res) => {
    //get info
    const { userID,job, address, age, bio, birth_date, first_name, joined_at, last_name, mobile} = req.body;
    sequelize.sync().then(() => {
    userProfile.update(
        { 
            job: job,
            address: address,
            age: age,
            bio: bio,
            birth_date: birth_date,
            first_name: first_name,
            joined_at: joined_at,
            last_name: last_name,
            mobile: mobile
        },
        {
            where: { user_id: userID }
        }
    ).then(response => {
        res.status(200).send("Data updated");
    }).catch((error) => {
        res.status(500).send("Error occurred during update");
    });
}).catch((error) => {
    res.status(500).send("Database connection error");
});
});
app.listen(3000);