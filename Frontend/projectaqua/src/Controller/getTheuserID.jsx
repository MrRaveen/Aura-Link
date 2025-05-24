/* eslint-disable react-hooks/rules-of-hooks */
/* eslint-disable no-unused-vars */
import { useCookies } from 'react-cookie';
//gets the stored user ID (loged user's)
function getIDprocess(){
     const [cookies] = useCookies(['user']);
     return cookies.user;
}
export default getIDprocess;