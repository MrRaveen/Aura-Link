/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import { Navigate, useLocation } from 'react-router-dom';
import { DotLottieReact } from '@lottiefiles/dotlottie-react';
import '../CSS/loginPage.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import React, { useRef } from 'react';
import CheckVerification from '../Controller/checkVerification';

const VerificationCodePageView = () => {
     const verificationCode = useRef();
     const location = useLocation();
     //get the details
     const { id,imageProfile } = location.state || {};

     //navigate obj
     const navigate = useNavigate();

     const hadleVerificationForm = async () => {
        if(verificationCode.current.value == ""){
            alert('Verification code is empty');
        }else{
            //check verification
            const obj1 = new CheckVerification();
            const result = await obj1.checkVerificationFun(id,verificationCode.current.value);
            if(result == 0){
                alert('No data found from temp DB');
            }
            else if(result == -1){
                alert('Verification code is incorrect');
            }
            else if(result == -2){
                alert('Values are null in the server');
            }
            else{
                //remove the temp data 
                const resultDelete = await obj1.removeTempDataByID(id);
                if(resultDelete == 'Removed'){
                alert('Removed temp data :: ' + resultDelete)//TO TEST FIXME:
                //save profile image 
                const resultImageInsert = await obj1.addProfileImage(imageProfile,result);
                if(resultImageInsert == 'Added'){
                  alert('Profile image added :: ' + resultImageInsert)
                }else{
                  alert('Profile image is not added :: ' + resultImageInsert)
                }
                }else{
                  alert('Temp data did not removed properly :: ' + resultDelete);
                }
                //redirect to the intrests page 
                const sendDataID = {
                  id : result
                };
                navigate('/inteColPage',{state : sendDataID});
            }

        }
     }
    return (
        <div className="bg-blue-800 min-h-screen">
  <div className="container" style={{ marginBottom: "12px" }}>
    <div className="row">
      <div className="col-md-12-sm-12 py-0">
        <div className="text-center mt-4 mb-3">
          <label className="text-6xl font-bold text-stone-100">Enter your verification code</label>
        </div>
      </div>
    </div>

    <div className="row">
      <div className="col-md-12 col-sm-12 py-0">
        <div className="formBoarder">
          <div className="row">
            {/* Left column: Form */}
            <div className="col-md-6 col-sm-6 py-0">
                <div className='centerDiv2'>
                <div>
                <label className='submitButton'>Verification code</label>
                <input className='input' ref={verificationCode} type="text" name="userName" id="userName" />
                <br /><br />
                <div className='submitButton'>
                <button className="m-1 rounded bg-indigo-900 border-2 flex items-center gap-2" onClick={()=>hadleVerificationForm()}>
                         Next
                        </button>
                </div>
                 </div>
                </div>
            </div>

            {/* Right column: GIF */}
            <div className="col-md-6 col-sm-6 py-0">
            <DotLottieReact
      src="https://lottie.host/31f18f91-69ed-4a40-9f3f-d3fec69e9660/f5qLCocKXk.lottie"
      loop
      autoplay
      style={{width:"600px"}}
    />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <br />

  {/* Footer */}
  <div className="container-fluid">
    <div className="bg-blue-600 h-32 flex items-center justify-center">
      <div className="text-center">
        <label className="text-stone-50">2024 Aura Link All rights received</label>
        <br />
        <label className="text-stone-50">New to pulse feed? </label>
        <a href="http://localhost:5173/" style={{ color: "white" }}>
          Create account here
        </a>
      </div>
    </div>
  </div>
</div>
    );
}
export default VerificationCodePageView;