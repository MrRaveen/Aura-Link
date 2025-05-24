/* eslint-disable no-unused-vars */
const PopupSection = ({onClose}) => {
    return(
        <div className="fixed inset-0 bg-black bg-opacity-40 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-2xl shadow-xl w-full max-w-lg relative">
        <button
          className="absolute top-3 right-3 text-gray-600 hover:text-black text-2xl"
          onClick={onClose}
        >
          &times;
        </button>
      </div>
    </div>
    );
};
export default PopupSection;