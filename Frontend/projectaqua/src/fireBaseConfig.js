/* eslint-disable no-unused-vars */
import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from "firebase/messaging";
const firebaseConfig = {
  apiKey: import.meta.env.VITE_APP_API_KEY,
  authDomain: import.meta.env.VITE_APP_AUTH_DOMAIN,
  projectId: import.meta.env.VITE_APP_PROJECT_ID,
  storageBucket: import.meta.env.VITE_APP_STORAGE_BUCKET,
  messagingSenderId: import.meta.env.VITE_APP_MESSAGING_SENDER_ID,
  appId: import.meta.env.VITE_APP_APP_ID
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

const messangingPart = async () => {
   const tokenGot = await getToken(messaging,{
        vapidKey: import.meta.env.VITE_APP_VAPID_KEY,
    });
    //console.log(tokenGot);//FIXME: TEST
    const data = {
        tokenGotOut: tokenGot,
        mesgOut: messaging
    }
    return data;
}
export default messangingPart;
