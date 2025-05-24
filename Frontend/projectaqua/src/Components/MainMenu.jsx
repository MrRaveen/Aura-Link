/* eslint-disable no-unused-vars */
import StickyNavbar from '../Components/navBar.jsx';
import firebaseConnect from '../Controller/firebaseConnect.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import React,{useState,useEffect} from 'react';
import { onMessage } from 'firebase/messaging';
import getIDprocess from '../Controller/getTheuserID';
const MainMenu = () => {
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
    return (
      <div>
       <StickyNavbar></StickyNavbar>
      </div>
    );
};
  
  export default MainMenu;