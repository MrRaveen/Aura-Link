/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import { useLocation } from 'react-router-dom';
import { DotLottieReact } from '@lottiefiles/dotlottie-react';
import '../CSS/loginPage.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import React, { useRef } from 'react';
import handleVerificationProcess from '../Controller/handleVerification';
const NormalCreateAccount2 = () => {
        const location = useLocation();
        //user entered data
        const userNameRef = useRef();
        const passRef = useRef();
        const emailRef = useRef();
        const consfPassRef = useRef();
        const picRef = useRef();
        //navigate obj
        const navigate = useNavigate();
        //previous page data
        const {fnameMain,lnameMain,bioTextMain,DOBMain,ageInMain,mobileMain,addressMain,jobMain} = location.state || {};
    
        const handleVerification = async () => {
      const obj1 = new handleVerificationProcess(userNameRef.current.value,emailRef.current.value);
      try{
        const result = await obj1.checkEligibility();
        if(result != ""){
            alert(result);
        }else{
          if(passRef.current.value != consfPassRef.current.value){
            alert("Passwords are not equal");
          }else{
            //save point of data (temp) --> mongo DB
            const tempDataResult = await obj1.saveDataTempToMongo(userNameRef.current.value,emailRef.current.value,passRef.current.value,fnameMain,lnameMain,bioTextMain,DOBMain,ageInMain,mobileMain,addressMain,jobMain);
            console.log(tempDataResult);
            if(tempDataResult == null || tempDataResult == 'Error occured'){
              alert('Data did not stored. try again later');
            }else{
              alert('Data stored temporaly : ' + tempDataResult.id)//test
              //TODO: Pass the values (including the profile image) and the retrived data object
              const sendingData = {id : tempDataResult.id,imageProfile : picRef.current.files[0]}
              navigate('/verificationView',{state: sendingData});
            }
          }
        }
      }catch(e){
        alert(e);
      }
    }
    return(
        <div className="bg-blue-800 min-h-screen">
  <div className="container" style={{ marginBottom: "12px" }}>
    <div className="row">
      <div className="col-md-12-sm-12 py-0">
        <div className="text-center mt-4 mb-3">
          <label className="text-6xl font-bold text-stone-100">Enter your password</label>
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
                <label>User Name</label>
                <br />
                <input className='input' ref={userNameRef} type="text" name="userName" id="userName" />
                <br />
                <label>Email</label>
                <br />
                <input className='input' ref={emailRef} type="email" name="userName" id="userName" />
                <br />

                <label>Password</label>
                <br />
                <input className='input' ref={passRef} type="password" name="userName" id="userName" />
                <br />

                <label>Confirm Password</label>
                <br />
                <input className='input' ref={consfPassRef} type="password" name="userName" id="userName" />
                <br />
                <label>Profile Picture</label>
                <br />
                <input className='input' ref={picRef} type="file" name="userName" id="userName" />
                <br /><br />
                <button className="m-1 rounded bg-indigo-900 border-2 flex items-center gap-2" onClick={()=> handleVerification()}>
                         Next
                        </button>
                 </div>
                </div>
            </div>

            {/* Right column: GIF */}
            <div className="col-md-6 col-sm-6 py-0">
            <DotLottieReact
      src="https://lottie.host/31f18f91-69ed-4a40-9f3f-d3fec69e9660/f5qLCocKXk.lottie"
      loop
      autoplay
      style={{width:"534px"}}
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
export default NormalCreateAccount2;

