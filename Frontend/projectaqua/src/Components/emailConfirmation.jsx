/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
import { useLocation } from 'react-router-dom';
import { useState } from 'react'; // ✅ Fixed import
import UpdateAccInfo from '../Controller/updateAccInfo';
import getIDprocess from '../Controller/getTheuserID';
import { useNavigate } from 'react-router-dom';

const EmailConfirmation = () => {
  const location = useLocation();
  const userIDIn = getIDprocess();
  const navigate = useNavigate();
  const { newUserName, newPassword } = location.state || {};
  const [formData, setFormData] = useState({
    verificationCode: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // ✅ Prevent page refresh
    //send the request
    try{
    if(newUserName != "" && newPassword != ""){
        var updateVerificationObj = new UpdateAccInfo(userIDIn,"","","","",0,"","",2,newUserName,newPassword);
        var verificationResult = await updateVerificationObj.checkVerification(formData.verificationCode);
        alert(verificationResult);
        if(verificationResult != "Invalid verification code"){
            navigate('/editProfile');           
        }
    }else{
        alert("Username and password is empty to send the code");
    }
    }catch(error){
    alert(error);
    }
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-md">
        <h1 className="text-xl font-bold mb-4 text-center text-gray-800">Email Confirmation</h1>
        <p className="text-gray-600 mb-6 text-center">
          Please enter the confirmation code sent to your email.
        </p>
        <form onSubmit={handleSubmit}> {/* ✅ Handler on form */}
          <div className="mb-4">
            <label
              htmlFor="confirmationCode"
              className="block text-sm font-medium text-gray-700"
            >
              Confirmation Code
            </label>
            <input
              value={formData.verificationCode}
              onChange={handleChange}
              required
              type="text"
              id="confirmationCode"
              name="verificationCode" // ✅ Added name attribute
              className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              placeholder="Enter your code"
            />
          </div>
          <button
            type="submit" // Keep as submit button
            className="w-full bg-indigo-600 text-white font-medium py-2 px-4 rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
          >
            Confirm
          </button>
        </form>
      </div>
    </div>
  );
};

export default EmailConfirmation;
