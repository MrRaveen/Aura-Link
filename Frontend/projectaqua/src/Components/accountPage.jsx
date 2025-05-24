    /* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import '../CSS/index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faComment, faUser, faImages, faUsers, faUserPlus, faPenFancy, faPenAlt } from '@fortawesome/free-solid-svg-icons';
import StickyNavbar from '../Components/navBar.jsx';
import React,{useState,useEffect} from 'react'
import { useNavigate } from 'react-router-dom';
import UserAccountPersonal from '../Controller/UserAccountPersonal.jsx';

import { onMessage } from 'firebase/messaging';
import getIDprocess from '../Controller/getTheuserID';
import firebaseConnect from '../Controller/firebaseConnect.jsx';

//TODO: use mappings - later
function AccountPage() {
    //TODO: make this reusable
     var obtainedUserID = getIDprocess();
  useEffect(()=>{
      async function process(){
        const message = await firebaseConnect(obtainedUserID);
        console.log(message);//FIXME:TEST
        //handle messages (get only)
        onMessage(message, (payload)=>{
          console.log('Recieved message to mainmenu : ' + payload); //FIXME: TEST
        });
      }
      process();
  },[]);

    const UserAccObj = new UserAccountPersonal();
    const navigate = useNavigate();
     const navigateToImagePage = (dataObject,postID) => {
        navigate('/ViewAllImages',{state : {data : dataObject,postid : postID}});
    }
    UserAccObj.getAccInfo().then(data => {
    document.getElementById('mainName').innerText = data.fname + " " + data.lname;
    document.getElementById('email').innerText = data.email;
    document.getElementById('postCount').innerText = data.postCount;
    document.getElementById('followersCount').innerText = data.followersCount;
    document.getElementById('followingCount').innerText = data.followingCount;
    document.getElementById('bio').innerText = data.bio;
    document.getElementById('job').innerText = "Job: "+data.job;
    document.getElementById('dob').innerText = "Date of birth: "+data.dob;
document.getElementById('profile_pic_url').innerHTML = `
    <img 
        src="${data.profile_pic_url}" 
        alt="Profile" 
        class="w-32 h-32 rounded-full border-4 border-white shadow-xl transform transition duration-300 hover:scale-105"
    />
`;
    });

    const handleCreatePostPage = () => {
        navigate('/createPost');
    }

    //posts
    var count = 0;
    const outPosts = UserAccObj.getAllPosts();
    console.log('Loading posts..');
    console.log(outPosts);
    outPosts.then(result => {
    const out3 = result.out3;//gets an array (post content table)
    const out1 = result.out1;//gets an array (main post table)
    const reaction = result.reaction;//gets an array
    out1.forEach(postHolder => {
        if(postHolder.content_type == "TEXT"){
            var allReactionsForText = reaction[count];
            //requiredData.forEach(contentJSONholder => {});
             const contentsForText = document.createElement("div");
contentsForText.innerHTML = `
    <article class="w-full bg-gray-700 shadow p-4 space-y-2 rounded-md hover:-translate-y-2 duration-300">
        <h2 style="color: white; font-weight: 500;">${postHolder.title}</h2>
        <p class="text-gray-300 text-sm">
            ${postHolder.description}
        </p>
        <div class="flex"><p style="color: white; font-weight: 500;" class="text-center mb-1">Date: ${postHolder.date}</p></div>
    <div class="flex"><p class="text-center mb-1 mb-1" style="color: white; font-weight: 500;">Time: ${postHolder.time}</p></div>  
    <div class="bg-gray-200 rounded flex space-x-4 items-center justify-center"><label>👍 ${allReactionsForText.like}</label><label>😆 ${allReactionsForText.laugh}</label><label>🥺 ${allReactionsForText.sad}</label><label>❤️ ${allReactionsForText.love}</label></div>
    <button class='bg-blue-600 rounded'>Comments</button>
    </article><br/>`; 
document.getElementById('articleSection').appendChild(contentsForText);    //no contents
            console.log('No contents');//FIXME: test
        }else{
            //append contents
            var requiredData = out3[count];//post contents array set
            var allReactions = reaction[count];
            //requiredData.forEach(contentJSONholder => {});
             const contents = document.createElement("div");
    contents.className = "relative group overflow-hidden rounded-xl shadow-md hover:shadow-xl transition duration-300";
    
    // Use random ID or data attributes instead
    const uniqueId = `viewBtn_${Math.random().toString(36).substr(2, 9)}`;
    
    contents.innerHTML = `
        <div class="relative w-full h-64">
            <img src="http://127.0.0.1:10000/devstoreaccount1/postcontents/${requiredData[0].media_name}" 
                 alt="test" 
                 class="w-full h-full object-cover">
            <button data-view-button 
                    value="${encodeURIComponent(JSON.stringify(requiredData))}" 
                    class="absolute left-2 bottom-2 z-10 bg-white bg-opacity-80 text-black p-2 rounded shadow">
                View all images (${requiredData.length})
            </button>
        </div>
         </div>
    <div class="mt-2 items-center justify-center flex"><h3>${postHolder.title}</h3></div>
    <div class="flex items-center justify-center">
     <p class="text-center mb-1">${postHolder.description}</p>
    </div>
    <div class="flex items-center justify-center"><p class="text-center mb-1">${postHolder.date}</p></div>
    <div class="flex items-center justify-center"><p class="text-center mb-1 mb-1">${postHolder.time}</p></div>  
    <div class="bg-gray-200 flex space-x-4 items-center justify-center"><label>👍 ${allReactions.like}</label><label>😆 ${allReactions.laugh}</label><label>🥺 ${allReactions.sad}</label><label>❤️ ${allReactions.love}</label></div>
    <div class="bg-indigo-800">
    <button class="text-center">Comments 🗨️</button></div>`;
    
    // Get the SPECIFIC button from THIS card
    const btn = contents.querySelector('[data-view-button]');
    btn.addEventListener('click', () => {
        const postID = postHolder.postid;
        const data = JSON.parse(decodeURIComponent(btn.value));
        navigateToImagePage(data,postID);
    });
    
    document.getElementById('contentGallery').appendChild(contents);
        }
        count++;
    });
});
     return (
        <div>
            <StickyNavbar></StickyNavbar>
            <div className="min-h-screen bg-gray-50 flex flex-col lg:flex-row">
            {/* Sidebar/Profile Section */}
            <div className="w-full lg:w-1/4 bg-white p-8 shadow-lg lg:min-h-screen">
                <div className="flex flex-col items-center space-y-4">
                    <div className="relative group" id='profile_pic_url'>
                     
                        <div className="absolute inset-0 rounded-full border-2 border-transparent group-hover:border-pink-300 transition-all duration-300"></div>
                    </div>
                    <div className="text-center">
                        <h2 className="text-2xl font-bold text-gray-800 mb-1" id='mainName'></h2>
                        <p className="text-gray-600 text-sm" id='email'>@adventure_john</p>
                        <button className="rounded group group-hover:before:duration-500 group-hover:after:duration-500 after:duration-500 hover:border-rose-300 hover:before:[box-shadow:_20px_20px_20px_30px_#a21caf] duration-500 before:duration-500 hover:duration-500 hover:after:-right-8 hover:before:right-12 hover:before:-bottom-8 hover:before:blur origin-left hover:decoration-2 hover:text-rose-300 relative bg-neutral-800 h-16 w-64 border text-left p-3 text-gray-50 text-base font-bold rounded-lg  overflow-hidden  before:absolute before:w-12 before:h-12 before:content[''] before:right-1 before:top-1 before:z-10 before:bg-violet-500 before:rounded-full before:blur-lg  after:absolute after:z-10 after:w-20 after:h-20 after:content['']  after:bg-rose-300 after:right-8 after:top-3 after:rounded-full after:blur-lg">
                         <FontAwesomeIcon icon={faUserPlus} className="mr-2" /> Edit
                        </button>
                        <button onClick={()=>handleCreatePostPage()} class="rounded group group-hover:before:duration-500 group-hover:after:duration-500 after:duration-500 hover:border-rose-300 hover:before:[box-shadow:_20px_20px_20px_30px_#a21caf] duration-500 before:duration-500 hover:duration-500 hover:after:-right-8 hover:before:right-12 hover:before:-bottom-8 hover:before:blur origin-left hover:decoration-2 hover:text-rose-300 relative bg-neutral-800 h-16 w-64 border text-left p-3 text-gray-50 text-base font-bold rounded-lg  overflow-hidden  before:absolute before:w-12 before:h-12 before:content[''] before:right-1 before:top-1 before:z-10 before:bg-violet-500 before:rounded-full before:blur-lg  after:absolute after:z-10 after:w-20 after:h-20 after:content['']  after:bg-rose-300 after:right-8 after:top-3 after:rounded-full after:blur-lg">
                         <FontAwesomeIcon icon={faPenAlt} className="mr-2" /> Create post
                        </button>
                    </div>
                </div>

                <div className="mt-8 space-y-4">
                    <div className="flex justify-around bg-gradient-to-r from-blue-50 to-purple-50 p-4 rounded-xl shadow-sm">
                        <div className="text-center">
                            <FontAwesomeIcon icon={faImages} className="text-blue-500 text-xl mb-1" />
                            <p className="font-bold text-gray-800" id='postCount'>0</p>
                            <p className="text-xs text-gray-500">Posts</p>
                        </div>
                        <div className="text-center">
                            <FontAwesomeIcon icon={faUsers} className="text-purple-500 text-xl mb-1" />
                            <p className="font-bold text-gray-800" id='followersCount'>0</p>
                            <p className="text-xs text-gray-500">Followers</p>
                        </div>
                        <div className="text-center">
                            <FontAwesomeIcon icon={faUser} className="text-pink-500 text-xl mb-1" />
                            <p className="font-bold text-gray-800" id='followingCount'>0</p>
                            <p className="text-xs text-gray-500">Following</p>
                        </div>
                    </div>

                    <div className="bg-white p-4 rounded-lg border border-gray-100 shadow-sm">
                        <p className="text-sm text-gray-600 leading-relaxed" id='bio'>
                        </p>
                    </div>
                    <div className="bg-white p-4 rounded-lg border border-gray-100 shadow-sm">
                        <p className="text-sm text-gray-600 leading-relaxed" id='job'>
                        </p>
                    </div>
                    <div className="bg-white p-4 rounded-lg border border-gray-100 shadow-sm">
                        <p className="text-sm text-gray-600 leading-relaxed" id='dob'>
                        </p>
                    </div>
                </div>
            </div>

            {/* Gallery Section */}
           <div className="w-full lg:w-3/4 p-6">
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4 relative" id='contentGallery'>
        {/* items are appended */}
    </div>
    
    {/* Article section below the gallery */}
    <div className="mt-8" id='articleSection'>  {/* Added margin-top for spacing */}
       
    </div>
</div>
        </div>
        
        </div>
    );
}

export default AccountPage;