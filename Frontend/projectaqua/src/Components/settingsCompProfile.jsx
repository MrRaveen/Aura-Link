/* eslint-disable no-unused-vars */
import React from "react";
import UserAccountPersonal from "../Controller/UserAccountPersonal";
const SettingsProfile = () => {
    const userInfoObject = new UserAccountPersonal()
    userInfoObject.getAccInfo().then(data => {
        document.getElementById('name').innerText = data.fname + " " + data.lname;
        document.getElementById('email').innerText = data.email;
        document.getElementById('postCount').innerText = data.postCount;
        document.getElementById('followersCount').innerText = data.followersCount;
        document.getElementById('followingCount').innerText = data.followingCount;
        document.getElementById('bio').innerText = data.bio;
        document.getElementById('job').innerText = "Job: "+data.job;
        document.getElementById('dob').innerText = "Date of birth: "+data.dob;
    document.getElementById('profileImage').innerHTML = `
  <img 
    src="${data.profile_pic_url}"
    alt="User profile picture"
    class="w-32 h-32 object-cover rounded-full border-4 border-white shadow-lg
           transform transition duration-300 ease-in-out 
           hover:scale-105 focus:scale-105 focus:outline-none
           cursor-pointer ring-2 ring-blue-400/30 hover:ring-blue-500/50"
  />
`;
    })
  return (
    <div className="max-w-md mx-auto mt-10 p-5 bg-white rounded-2xl shadow-md">
      {/* Profile Picture and Name */}
      <div className="flex flex-col items-center" id="profileImage">
        
      </div>
      <div className="flex flex-col items-center">
        <h2 className="mt-3 text-xl font-semibold" id="name"></h2>
        <p className="text-sm text-gray-500" id="email"></p>
      </div>

      {/* Buttons */}
      <div className="flex justify-center mt-1 space-x-3">
        <button className="px-4 py-2 text-sm font-medium text-white bg-blue-500 rounded-full shadow-md hover:bg-blue-600">
          View your posts
        </button>
      </div>

      {/* Stats */}
      <div className="flex justify-around mt-5 border-t pt-3">
        <div className="text-center">
          <p className="text-xl font-semibold">Posts</p>
          <p className="text-sm text-gray-500" id="postCount"></p>
        </div>
        <div className="text-center">
          <p className="text-xl font-semibold">Followers</p>
          <p className="text-sm text-gray-500" id="followersCount"></p>
        </div>
        <div className="text-center">
          <p className="text-xl font-semibold">Following</p>
          <p className="text-sm text-gray-500" id="followingCount"></p>
        </div>
      </div>

      {/* Additional Information */}
      <div className="mt-5 space-y-3">
        <div className="p-3 bg-gray-100 rounded-lg shadow-inner">
          <p id="bio"></p>
        </div>
        <div className="p-3 bg-gray-100 rounded-lg shadow-inner">
          <p id="job"></p>
        </div>
        <div className="p-3 bg-gray-100 rounded-lg shadow-inner">
          <p id="dob"></p>
        </div>
      </div>
    </div>
  );
};
export default SettingsProfile;