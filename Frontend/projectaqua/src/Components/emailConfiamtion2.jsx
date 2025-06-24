/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
import React from "react";
import { useNavigate } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import { useState } from 'react'; 
import UpdateAccInfo from "../Controller/updateAccInfo";
const EmailConfirmation2 = () => {
  const location = useLocation();
  const navigate = useNavigate();
   const { userID, newEmail } = location.state || {};
  const [formData, setFormData] = useState({
    verificationCode: ""
  });
   const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  const handleSendClick = async (e) => {
     e.preventDefault();
     //send request
     if(formData.verificationCode != "" || formData.verificationCode != null){
           var requestResult = await UpdateAccInfo.updateEmailSendRequest(newEmail,userID,2,formData.verificationCode);
           alert(requestResult);
           //navigate back
           navigate('/editProfile');
     }else{
      alert('verification code is empty');
     }
  };
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="max-w-md w-full bg-white rounded-2xl shadow-md p-8">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-6">
          Confirm Your Email
        </h2>
        <p className="text-sm text-gray-600 text-center mb-4">
          We’ve sent a code to your email. Please enter it below to confirm.
        </p>
        <form onSubmit={handleSendClick}>
          <label htmlFor="code" className="block text-sm font-medium text-gray-700 mb-2">
            Confirmation Code
          </label>
          <input
            type="text"
            name="verificationCode"
              value={formData.verificationCode}
              onChange={handleChange}
            placeholder="Enter verification code"
            className="w-full px-4 py-2 mb-4 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button
            type="submit"
            className="mt-6 w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition duration-300"
          >
            Confirm Email
          </button>
        </form>
        <div className="mt-4 text-center text-sm text-gray-600">
          Didn’t receive the code? <span className="text-blue-600 hover:underline cursor-pointer">Resend</span>
        </div>
      </div>
    </div>
  );
};

export default EmailConfirmation2;
