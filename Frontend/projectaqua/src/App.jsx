/* eslint-disable no-unused-vars */
import LogInPage from './Components/loginPage.jsx';
// eslint-disable-next-line no-unused-vars
import MainMenu from './Components/MainMenu.jsx';
// eslint-disable-next-line no-unused-vars
import CreateAccount from './Components/createAccount.jsx';
import NormalCreateAccount from './Components/normalCreateAccount.jsx';
import NormalCreateAccount2 from './Components/normalCreateAccount2.jsx';
import VerificationCodePageView from './Components/varificationCodePage.jsx';
import InterestsColPage from './Components/interestsColPage.jsx';
import AccountPage from './Components/accountPage.jsx';
import ViewAllImages from './Components/viewAllImages.jsx';
import { CookiesProvider, useCookies } from 'react-cookie';
import NotificationPage from './Components/notificationPage.jsx';
import CreatePost from './Components/createPost.jsx';
import EditProfile from './Components/editProfile.jsx';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
function App(){
  return(
           <CookiesProvider>
              <>
    <Router>
      <Routes>
        <Route path='/' element={<LogInPage/>}/>
        <Route path='/mainMenuTo' element={<MainMenu/>}/>
        <Route path='/createAcc' element={<CreateAccount/>}/>
        <Route path='/NormalCreateAccPath' element={<NormalCreateAccount/>}/>
        <Route path='/NormalCreateAccPath2' element={<NormalCreateAccount2/>}/>
        <Route path='/verificationView' element={<VerificationCodePageView/>}/>
        <Route path='/inteColPage' element={<InterestsColPage/>}/>
        <Route path='/accountPath' element={<AccountPage/>}/>
        <Route path = '/ViewAllImages' element={<ViewAllImages/>}/>
        <Route path = '/notificationPage' element = {<NotificationPage/>}/>
        <Route path = '/createPost' element = {<CreatePost/>}/>  
        <Route path='/editProfile' element = {<EditProfile/>}/>   
      </Routes>
    </Router>
    </>
           </CookiesProvider>
  
  );
}
export default App