/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
/* eslint-disable no-unused-vars */
import { useState } from 'react';
import { useLocation } from 'react-router-dom';

const EmailConfirmation2 = () => {
  const location = useLocation();
  const { userID, newEmail } = location.state || {};
  const [code, setCode] = useState(['', '', '', '', '', '']);
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Handle input changes
  const handleChange = (index, value) => {
    if (/^\d*$/.test(value) && value.length <= 1) {
      const newCode = [...code];
      newCode[index] = value;
      setCode(newCode);
      
      // Auto-focus next input
      if (value && index < 5) {
        document.getElementById(`code-input-${index + 1}`).focus();
      }
    }
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    const verificationCode = code.join('');
    
    // Your verification logic here
    console.log("Verifying code:", verificationCode);
    console.log("For user:", userID);
    console.log("With email:", newEmail);
    
    // Simulate API call
    setTimeout(() => {
      setIsSubmitting(false);
      alert(`Code ${verificationCode} submitted for verification`);
    }, 1500);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8 bg-white p-8 rounded-xl shadow-md">
        <div>
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Verify Your Email
          </h2>
          <p className="mt-2 text-center text-sm text-gray-600">
            We've sent a 6-digit code to: 
            <span className="font-medium ml-1 text-blue-600">{newEmail}</span>
          </p>
        </div>

        <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
          <div className="flex justify-center space-x-3">
            {code.map((digit, index) => (
              <input
                key={index}
                id={`code-input-${index}`}
                type="text"
                maxLength={1}
                value={digit}
                onChange={(e) => handleChange(index, e.target.value)}
                className="w-12 h-12 text-center text-2xl border-2 border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
                autoFocus={index === 0}
              />
            ))}
          </div>

          <div>
            <button
              type="submit"
              disabled={isSubmitting || code.some(d => d === '')}
              className={`w-full flex justify-center py-3 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white ${
                isSubmitting || code.some(d => d === '') 
                  ? 'bg-gray-400 cursor-not-allowed' 
                  : 'bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'
              }`}
            >
              {isSubmitting ? (
                <>
                  <svg className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                    <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  Verifying...
                </>
              ) : 'Verify Code'}
            </button>
          </div>
        </form>

        <div className="text-center mt-4">
          <p className="text-sm text-gray-600">
            Didn't receive the code? 
            <button className="ml-2 font-medium text-blue-600 hover:text-blue-500">
              Resend
            </button>
          </p>
        </div>
      </div>
    </div>
  );
};

export default EmailConfirmation2;