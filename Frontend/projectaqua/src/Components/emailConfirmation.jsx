/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
import { useLocation,useState } from 'react-router-dom';

const EmailConfirmation = () => {
    const location = useLocation();
    const { newUserName, newPassword } = location.state || {};
      const [formData, setFormData] = useState({
        verificationCode: ""
      });
      const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
    const handleButtonSubmit = () => {
        
        alert("sent " + formData.verificationCode);
    }
    return (
        <div className="flex justify-center items-center h-screen bg-gray-100">
            <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-md">
                <h1 className="text-xl font-bold mb-4 text-center text-gray-800">Email Confirmation</h1>
                <p className="text-gray-600 mb-6 text-center">
                    Please enter the confirmation code sent to your email.
                </p>
                <form>
                    <div className="mb-4">
                        <label
                            htmlFor="confirmationCode"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Confirmation Code
                        </label>
                        <input
                            value={formData.verificationCode}
                            onChange={handleChange}
                            required
                            type="text"
                            id="confirmationCode"
                            className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                            placeholder="Enter your code"
                        />
                    </div>
                    <button
                        onClick={handleButtonSubmit}
                        type="submit"
                        className="w-full bg-indigo-600 text-white font-medium py-2 px-4 rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                    >
                        Confirm
                    </button>
                </form>
            </div>
        </div>
    );
};

export default EmailConfirmation;
