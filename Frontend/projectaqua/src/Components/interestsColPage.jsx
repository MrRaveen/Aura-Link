/* eslint-disable no-unused-vars */
import '../CSS/loginPage.css';
import '../CSS/interests.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation } from 'react-router-dom';
import HandleInterestSend from '../Controller/handleInteretSend';
import { useNavigate } from 'react-router-dom';

const InterestsColPage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { id } = location.state || {};
    const insertProcess = () => {
        var currentChoice = document.getElementById('InterestName').value;
        document.getElementById('cat').value = currentChoice;
    }
    const handleSubmit = async () => {
        //call the function TODO:
        const currentCat = document.getElementById('cat').value;
        const curretnName = document.getElementById('InterestName').value;
        const obj1 = new HandleInterestSend(id,curretnName,currentCat);
        const resultInterest = await obj1.addingProcess();
        if(resultInterest === 'Entered'){
            alert('Data inserted');
        }else{
            alert('Data not inserted :: ' + resultInterest);
        }
    }
    const handleNext = () => {
      navigate('/mainMenuTo');
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
              What are your interests?
            </label>
          </div>
        </div>
      </div>

      {/* Form row */}
      <div className="row">
        <div className="col-md-12-sm-12 py-4">
          <div className="centerDiv2">
            <div className="bg-slate-200 p-10 rounded">
              <label>Interest Name</label>
              <br />
              <select name="InterestName" id="InterestName" onChange={()=>insertProcess()}> 
                 <option value="Photography:Art">Photography</option> 
                 <option value="Hiking:Outdoor">Hiking</option> 
                 <option value="Travel:Leisure">Travel</option> 
                 <option value="Japan:Culture">Japan</option> 
                 <option value="Music:Entertainment">Music</option>
                 <option value="Reading:Education">Reading</option>
                 <option value="Cooking:Lifestyle">Cooking</option>
                 <option value="Gaming:Entertainment">Gaming</option>
                 <option value="Fitness:Health">Fitness</option>
                 <option value="Art:Art">Art</option>
              </select>
              <br /><br />
              <label>Interest Category</label>
              <br />
              <input type="text" name="cat" id="cat" required disabled />
              <br /><br />
              <div className="centerDiv2">
                <button className="m-4 rounded bg-indigo-900" onClick={()=>handleSubmit()}>
                  Add
                </button>
              </div>
              <div className="centerDiv2">
                <button className="m-0 rounded bg-indigo-900" onClick={()=> handleNext()}>
                  Next
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
export default InterestsColPage;