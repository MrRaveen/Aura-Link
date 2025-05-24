/* eslint-disable no-unused-vars */
/* eslint-disable no-empty */
import '../CSS/loginPage.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { CookiesProvider, useCookies } from 'react-cookie'
//import HandleLogIn from '../Controller/loginProcess';
import React, { useRef } from 'react';
const LogInPage = () => {
    //for cookies
    const [cookies, setCookie] = useCookies(['user'])

    const userNameRef = useRef();
    const passRef = useRef();
    const navigate = useNavigate();
    const HandleSocialLogIn = async () => {
        window.location.href = "http://localhost:8002"; 
    }
    function getTheuserID(userName){
              axios.get(`http://localhost:8090/api/auth/getUserID?username=${userName}`, {
              })
              .then(response => {
                if(response.status === 200) {
                    //store the cookie
                    setCookie('user', response.data, { path: '/' })
                    navigate('/mainMenuTo'); 
                }
              })
              // eslint-disable-next-line no-unused-vars
              .catch(error => {
                alert('Login failed: Invalid username or password');
              });    
    }
    const HandleLogIn = (userName, Password) => {
        if(userName != "" || Password != ""){
            axios.post('http://localhost:8090/api/auth/login', {
                username: userName,
                password_hash: Password
              },{withCredentials: true})
              .then(response => {
                if(response.status === 200) {
                     // navigate('/mainMenuTo'); 
                    getTheuserID(userName);
                }
              })
              // eslint-disable-next-line no-unused-vars
              .catch(error => {
                alert('Login failed: Invalid username or password');
              });
        }else{
            alert('Username or Password is empty');
        }
    }
    return (
        <div className="bg-blue-800 min-h-screen">
         <div className="container" style={{marginBottom: "12px"}}>
        <div className="row">
        <div className="text-center mt-4 mb-3"><label className="text-6xl font-bold text-stone-100">Aura Link</label></div>
        </div>
        <div className="row">
        <div className="centerDiv">
        <div className='formBoarder'>
            <label>User Name</label><br></br>
            <input ref={userNameRef} type="text" name="userName" id="userName" className='input'></input><br></br>
            <label>Password</label><br></br>
            <input ref={passRef} type="password" name="userName" id="userName" className='input'></input><br></br>
            <div className='submitButton'><button className="m-4 rounded bg-indigo-900" onClick={()=>HandleLogIn(userNameRef.current.value,passRef.current.value)}>Log In</button></div>
            <button className="m-0 rounded bg-blue-500 border-2 flex items-center gap-2" onClick={() => HandleSocialLogIn()}>
            <img src="https://www.vectorlogo.zone/logos/google/google-tile.svg" alt="Google Logo" className="w-6 h-6" />Log In with Google</button>
            
        </div>
        
         </div>
        </div>
    </div><br />
    <div className="container-fluid">
        <div className="bg-blue-600 h-32 flex items-center justify-center">
        
            <div className="text-center">
                <label className="text-stone-50">2024 PulseFeed All rights recieved</label><br></br>
                <label className="text-stone-50">New to pulse feed? </label><a href='http://localhost:5173/createAcc' style={{color : "white"}}>Create account here</a>
            </div>
        </div>
    </div>
       </div>
    );
}

export default LogInPage;