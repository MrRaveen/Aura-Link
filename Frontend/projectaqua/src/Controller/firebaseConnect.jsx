/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import React,{useState,useEffect} from 'react';
import messangingPart from '../fireBaseConfig';
import axios from 'axios';


const firebaseConnect = async (obtainedUserID) => {
    const permission = await Notification.requestPermission();
    const dataGot = await messangingPart();
    /**
     * fR4QH7wCwXld-v3IZmR5Vz:APA91bGOGMMSYHm7eBwab6pKdaexp946vPiU6n98BxyjmKEIUpx6JwJmEouYQ3HJTuyxyPPZNwkgCZUPuz5dMgxYD1lMpDBJxXFsJ4dWpfJpNYYFbWte4z8
     */
           //TODO: register device
           axios.post('http://localhost:8020/api/userAccount/registerUserDevice',{
            id: obtainedUserID,
            addressLong: dataGot.tokenGotOut.toString()
           })
          .then(response => {
            if(response.status === 202 || response.status === 200) {
                console.log('User registered : ' + response.data);//FIXME: remove the token
            }else{
               alert('Cannot register the user device try again : ' + response.data);
            }
          })
          .catch(error => {
            alert('Error occured when registering device : ' + error);
          });
    const message = await dataGot.mesgOut;
    return message;
}

export default firebaseConnect;