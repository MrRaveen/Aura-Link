/* eslint-disable no-unused-vars */
import React, { useState,useEffect } from "react";
import UpdateAccInfo from "../Controller/updateAccInfo";
import getIDprocess from '../Controller/getTheuserID';
import GetProfileInfo from "../Controller/getProfileInfo";
import { useNavigate } from 'react-router-dom';
const UpdateSection = () => {
  const userIDIn = getIDprocess();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    userID: "",
    first_name: "",
    last_name: "",
    bio: "",
    profile_pic_url: "",
    birth_date: "",
    mobile: "",
    address: "",
    job: "",
    userName: "",
    password: "",
    choice: "",
  });

  const [responseMessage, setResponseMessage] = useState("");
  const [isUsernamePopupOpen, setIsUsernamePopupOpen] = useState(false);
  const [isEditEmailOpen, setIsEditEmailOpen] = useState(false);
  const [isProfileImagePopupOpen, setIsProfileImagePopupOpen] = useState(false);
  const [draggedImage, setDraggedImage] = useState(null);
  const [newEmail, setNewEmail] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  useEffect(() => {
  return () => {
    if (draggedImage) {
      URL.revokeObjectURL(draggedImage); // Clean up preview URLs
    }
  };
}, [draggedImage]);
  // const handleImageDrop = (e) => {
  //   e.preventDefault();
  //   const file = e.dataTransfer.files[0];
  //   if (file) {
  //     const reader = new FileReader();
  //     reader.onload = () => {
  //       setDraggedImage(reader.result);
  //       setFormData({ ...formData, profile_pic_url: reader.result });
  //     };
  //     reader.readAsDataURL(file);
  //   }
  // };
  const [imageFile, setImageFile] = useState(null);
  const handleImageDrop = (e) => {
  e.preventDefault();
  const file = e.dataTransfer.files[0];
  if (file) {
    const objectUrl = URL.createObjectURL(file); // For preview
    setDraggedImage(objectUrl);
    // Store the actual FILE object in state
    setImageFile(file); 
  }
};

  const handleImageDragOver = (e) => {
    e.preventDefault();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      //send the request
      var updateObj = new UpdateAccInfo(userIDIn,formData.first_name,formData.last_name,formData.bio,formData.birth_date,formData.mobile,formData.address,formData.job,2,"","");
      var resultUpdateProfile = await updateObj.updateProcess();
      alert(resultUpdateProfile);
    } catch (error) {
      setResponseMessage("Error occured when updating profile info : " + error);
    }
  };
  const handleChangePassword = async (e) => {
    e.preventDefault();
      if(formData.userName != "" || formData.password != ""){
        var updateObj = new UpdateAccInfo(userIDIn,formData.first_name,formData.last_name,formData.bio,formData.birth_date,formData.mobile,formData.address,formData.job,1,"","");
        var resultUpdateProfile = await updateObj.updateProcess();
        if(resultUpdateProfile == "A varification code is sent to your email."){
        const sendingData = {newUserName : formData.userName,newPassword : formData.password}
        navigate('/emailConfirmation',{state: sendingData});
        }else{
          alert(resultUpdateProfile);
        }
      }else{
        alert("Username and password must not be empty");
      }
  }
  const handleSubmitUpdateProfileImg = async (e) => {
    e.preventDefault();
    try{
      if(!imageFile){
        alert("Select an image");
      }
      else{
        var updateImgResult = await UpdateAccInfo.updateProfileImg(userIDIn,imageFile);
      }
    }catch(error){
      alert('Error occured when updating image : ' + error);
    }finally{
      if(imageFile){
        alert(updateImgResult);
        window.location.reload();
      }
    }
  };

  function validateEmail(email) {
  const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
  return regex.test(email);
  }
  const handleEmailUpdate = async () => {
  if (!validateEmail(newEmail)) {
    alert('Please enter a valid email address');
    return;
  }else{
navigate('/emailConfirmation2', {
  state: {
    userID: userIDIn,   // Your user ID from state
    newEmail: newEmail  // The new email from input
  }
});
  }
  };
  //loads the information 
 useEffect(() => {
    GetProfileInfo(userIDIn).then(data => {
      setFormData(prev => ({
        ...prev,
        first_name: data.first_name,
        last_name: data.last_name,
        bio: data.bio,
        birth_date: data.birth_date.split('T')[0],
        mobile: data.mobile,
        address: data.address,
        job: data.job
      }));
    });
  }, []); 
  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white rounded-lg shadow-xl">
      <h2 className="text-2xl font-bold mb-4">Update Profile</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <input
            type="text"
            name="first_name"
            required
            placeholder="First Name"
            value={formData.first_name}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
          <input
            type="text"
            required
            name="last_name"
            placeholder="Last Name"
            value={formData.last_name}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
          <input
            type="text"
            name="bio"
            placeholder="Bio"
            required
            value={formData.bio}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
          <input
            type="date"
            name="birth_date"
            required
            value={formData.birth_date}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
          <input
            type="number"
            name="mobile"
            placeholder="Mobile"
            required
            value={formData.mobile}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            required
            value={formData.address}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
          <input
            type="text"
            name="job"
            placeholder="Job"
            required
            value={formData.job}
            onChange={handleChange}
            className="p-2 border border-gray-300 rounded"
          />
        </div>
        <button
          type="submit"
          className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          Update Profile
        </button>
      </form>
      <button
        onClick={() => setIsUsernamePopupOpen(true)}
        className="mt-4 w-full p-2 bg-green-500 text-white rounded hover:bg-green-600"
      >
        Update Username & Password
      </button>
      <button
        onClick={() => setIsEditEmailOpen(true)}
        className="mt-4 w-full p-2 bg-red-500 text-white rounded hover:bg-green-600"
      >
        Update your email
      </button>
      <button
        onClick={() => setIsProfileImagePopupOpen(true)}
        className="mt-4 w-full p-2 bg-purple-500 text-white rounded hover:bg-purple-600"
      >
        Update Profile Image
      </button>

      {isUsernamePopupOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-xl w-1/3">
            <h3 className="text-xl font-bold mb-4">Update Username & Password</h3>
            <input
              type="text"
              name="userName"
              placeholder="New Username"
              required
              value={formData.userName}
              onChange={handleChange}
              className="p-2 border border-gray-300 rounded w-full mb-4"
            />
            <input
              type="password"
              name="password"
              placeholder="New Password"
              required
              value={formData.password}
              onChange={handleChange}
              className="p-2 border border-gray-300 rounded w-full mb-4"
            />
            <button
              className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600 mb-4"
              onClick={handleChangePassword}
            >
              Update
            </button>
            <button
              onClick={() => setIsUsernamePopupOpen(false)}
              className="w-full p-2 bg-red-500 text-white rounded hover:bg-red-600"
            >
              Close
            </button>
          </div>
        </div>
      )}

      {isProfileImagePopupOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-xl w-1/3">
            <h3 className="text-xl font-bold mb-4">Update Profile Image</h3>
            <div
              onDrop={handleImageDrop}
              onDragOver={handleImageDragOver}
              className="p-4 border-2 border-dashed border-gray-300 rounded-lg flex items-center justify-center cursor-pointer"
            >
              {draggedImage ? (
                <img
                  src={draggedImage}
                  alt="Preview"
                  className="h-24 w-24 object-cover rounded-full"
                />
              ) : (
                <p>Drag and drop an image here</p>
              )}
            </div>
            <button
              className="w-full p-2 mt-2 bg-blue-500 text-white rounded hover:bg-blue-600 mb-4"
              onClick={handleSubmitUpdateProfileImg}
            >
              Update
            </button>
            <button
              onClick={() => setIsProfileImagePopupOpen(false)}
              className="w-full p-2 bg-red-500 text-white rounded hover:bg-red-600"
            >
              Close
            </button>
          </div>
        </div>
      )}

      {isEditEmailOpen && (
  <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
    <div className="bg-white p-6 rounded-lg shadow-xl w-1/3">
      <h3 className="text-xl font-bold mb-4">Update Email</h3>
      
      {/* Replaced drag/drop area with email input */}
      <input
        type="email"
        value={newEmail}
        onChange={(e) => setNewEmail(e.target.value)}
        placeholder="Enter new email address"
        className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
        required
      />
      
      <button
        className="w-full p-2 mt-4 bg-blue-500 text-white rounded hover:bg-blue-600 mb-4"
        onClick={handleEmailUpdate}  // Changed to email handler
      >
        Update Email
      </button>
      <button
        onClick={() => setIsEditEmailOpen(false)}  // Close this specific popup
        className="w-full p-2 bg-red-500 text-white rounded hover:bg-red-600"
      >
        Close
      </button>
    </div>
  </div>
)}

      {responseMessage && (
        <p className="mt-4 text-center text-gray-700">{responseMessage}</p>
      )}
    </div>
  );
};

export default UpdateSection;
