import '../CSS/loginPage.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';
const CreateAccount = () => {
    const Navigate = useNavigate();
    const hadleForm = () => {
        Navigate('/NormalCreateAccPath');
    }
    const handleGoogleCreate = () => {
        window.location.href = "http://localhost:8000"; 
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
            
            <div style={{display : "flex",justifyContent: "center", alignItems: "center"}}>
            <button className="m-0 rounded bg-blue-500 border-2 flex items-center gap-2" onClick={()=>hadleForm()}>Create account</button>
            </div>
            <br></br>
            <button className="m-0 rounded bg-blue-500 border-2 flex items-center gap-2" onClick={()=>handleGoogleCreate()}>
            <img src="https://www.vectorlogo.zone/logos/google/google-tile.svg" alt="Google Logo" className="w-6 h-6" />Continue with Google</button>
        </div>
        
         </div>
        </div>
    </div>
    <div className="container-fluid">
        <div className="bg-blue-600 h-32 flex items-center justify-center">
        
            <div className="text-center">
                <label className="text-stone-50">2024 PulseFeed All rights recieved</label><br></br>
                
            </div>
        </div>
    </div>
       </div>
    );
}

export default CreateAccount;