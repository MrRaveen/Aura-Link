/* eslint-disable no-unused-vars */
import '../CSS/loginPage.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import React, { useRef } from 'react';
const NormalCreateAccount = () => {
    const navigate = useNavigate();
    const fname = useRef();
    const lname = useRef();
    const bioText = useRef();
    const DOB = useRef();
    const ageIn = useRef();
    const mobile = useRef();
    const address = useRef();
    const job = useRef();
    const handleForm = () => {
        if(fname.current.value == "" || lname.current.value == "" || bioText.current.value == "" || DOB.current.value == "" || ageIn.current.value == "" || mobile.current.value == "" || address.current.value == "" || job.current.value == ""){
            alert('Some values are empty');
        }else{
            const sendingData = {
                fnameMain: fname.current.value,  // Add .current
                lnameMain: lname.current.value,
                bioTextMain: bioText.current.value,
                DOBMain: DOB.current.value,
                ageInMain: ageIn.current.value,
                mobileMain: mobile.current.value,
                addressMain: address.current.value,
                jobMain: job.current.value
            }
            navigate('/NormalCreateAccPath2',{state: sendingData});
        }
    }
    return(
<div>
  <div className="bg-blue-800 min-h-screen">
    <div className="container" style={{ marginBottom: "12px" }}>
      
      {/* Header row */}
      <div className="row">
        <div className="col-md-12-sm-12 py-0">
          <div className="text-center mt-4">
            <label className="text-6xl font-bold text-stone-100">
              Create your account
            </label>
          </div>
        </div>
      </div>

      {/* Form row */}
      <div className="row">
        <div className="col-md-12-sm-12 py-4">
          <div className="centerDiv2">
            <div className="bg-slate-200 p-10 rounded">
              
              <label>First Name</label>
              <br />
              <input className='input' ref={fname} type="text" name="userName" id="userName" required />
              <br />

              <label>Last Name</label>
              <br />
              <input className='input' ref={lname} type="text" name="userName" id="userName" required />
              <br />

              <label>Bio</label>
              <br />
              <input className='input' ref={bioText} type="text" name="userName" id="userName" required />
              <br />

              <label>Date of birth</label>
              <br />
              <input className='input' ref={DOB} type="date" name="userName" id="userName" required />
              <br />

              <label>Age</label>
              <br />
              <input className='input' ref={ageIn} type="number" name="userName" id="userName" required />
              <br />

              <label>Mobile</label>
              <br />
              <input className='input' ref={mobile} type="number" name="userName" id="userName" required />
              <br />

              <label>Address</label>
              <br />
              <input className='input' ref={address} type="text" name="userName" id="userName" required />
              <br />

              <label>Job</label>
              <br />
              <input className='input' ref={job} type="text" name="userName" id="userName" required />
              <br /><br />

              <div className="centerDiv2">
                <button className="m-4 rounded bg-indigo-900" onClick={() => handleForm()}>
                  Next
                </button>
              </div>

              <div className="centerDiv2">
                <button className="m-0 rounded bg-indigo-900" onClick={() => { navigate('/'); }}>
                  Cancel
                </button>
              </div>

            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>

    );
}
export default NormalCreateAccount;