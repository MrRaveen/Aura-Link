import 'bootstrap/dist/css/bootstrap.min.css';
import '../CSS/index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faComment, faUser, faImages, faUsers, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import StickyNavbar from '../Components/navBar.jsx';

import UserAccountPersonal from '../Controller/UserAccountPersonal.jsx';

const photos = [
    '/img1.jpg', '/img2.jpg', '/img3.jpg', 
    '/img4.jpg', '/img5.jpg', '/img6.jpg', 
    '/img7.jpg', '/img8.jpg', '/img9.jpg'
];

const AccountPage = () => {
    const UserAccObj = new UserAccountPersonal();
    UserAccObj.getAccInfo().then(data => {
    console.log("First Name:", data.fname); // Access fname from resolved data
    document.getElementById('mainName').innerText = data.fname + " " + data.lname;
    document.getElementById('email').innerText = data.email;
    document.getElementById('postCount').innerText = data.postCount;
    document.getElementById('followersCount').innerText = data.followersCount;
    document.getElementById('followingCount').innerText = data.followingCount;
    document.getElementById('bio').innerText = data.bio;
    document.getElementById('job').innerText = "Job: "+data.job;
    document.getElementById('dob').innerText = "Date of birth: "+data.dob;
document.getElementById('profile_pic_url').innerHTML = `
    <img 
        src="${data.profile_pic_url}" 
        alt="Profile" 
        class="w-32 h-32 rounded-full border-4 border-white shadow-xl transform transition duration-300 hover:scale-105"
    />
`;
    });
//  console.log(UserAccObj.getAllPosts());
//  const outPosts = UserAccObj.getAllPosts();
//  const out3 = outPosts.out3;
//  out3.forEach(element => {
//     if(element!=null){

//     }
//  });
    return (
        <div>
            <StickyNavbar></StickyNavbar>
            <div className="min-h-screen bg-gray-50 flex flex-col lg:flex-row">
            {/* Sidebar/Profile Section */}
            <div className="w-full lg:w-1/4 bg-white p-8 shadow-lg lg:min-h-screen">
                <div className="flex flex-col items-center space-y-4">
                    <div className="relative group" id='profile_pic_url'>
                     
                        <div className="absolute inset-0 rounded-full border-2 border-transparent group-hover:border-pink-300 transition-all duration-300"></div>
                    </div>
                    <div className="text-center">
                        <h2 className="text-2xl font-bold text-gray-800 mb-1" id='mainName'></h2>
                        <p className="text-gray-600 text-sm" id='email'>@adventure_john</p>
                        <button className="mt-3 bg-gradient-to-r from-pink-500 to-purple-500 text-white px-6 py-2 rounded-full text-sm font-semibold shadow-lg hover:shadow-xl transition duration-300">
                            <FontAwesomeIcon icon={faUserPlus} className="mr-2" />
                            Edit
                        </button>
                    </div>
                </div>

                <div className="mt-8 space-y-4">
                    <div className="flex justify-around bg-gradient-to-r from-blue-50 to-purple-50 p-4 rounded-xl shadow-sm">
                        <div className="text-center">
                            <FontAwesomeIcon icon={faImages} className="text-blue-500 text-xl mb-1" />
                            <p className="font-bold text-gray-800" id='postCount'>0</p>
                            <p className="text-xs text-gray-500">Posts</p>
                        </div>
                        <div className="text-center">
                            <FontAwesomeIcon icon={faUsers} className="text-purple-500 text-xl mb-1" />
                            <p className="font-bold text-gray-800" id='followersCount'>0</p>
                            <p className="text-xs text-gray-500">Followers</p>
                        </div>
                        <div className="text-center">
                            <FontAwesomeIcon icon={faUser} className="text-pink-500 text-xl mb-1" />
                            <p className="font-bold text-gray-800" id='followingCount'>0</p>
                            <p className="text-xs text-gray-500">Following</p>
                        </div>
                    </div>

                    <div className="bg-white p-4 rounded-lg border border-gray-100 shadow-sm">
                        <p className="text-sm text-gray-600 leading-relaxed" id='bio'>
                        </p>
                    </div>
                    <div className="bg-white p-4 rounded-lg border border-gray-100 shadow-sm">
                        <p className="text-sm text-gray-600 leading-relaxed" id='job'>
                        </p>
                    </div>
                    <div className="bg-white p-4 rounded-lg border border-gray-100 shadow-sm">
                        <p className="text-sm text-gray-600 leading-relaxed" id='dob'>
                        </p>
                    </div>
                </div>
            </div>

            {/* Gallery Section */}
            <div className="w-full lg:w-3/4 p-6">
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
                    {photos.map((photo, index) => (
                        <div key={index} className="relative group overflow-hidden rounded-xl shadow-md hover:shadow-xl transition duration-300">
                            <img
                                src={photo}
                                alt={`Gallery ${index + 1}`}
                                className="w-full h-64 object-cover transform transition duration-300 group-hover:scale-105"
                            />
                            <div className="absolute inset-0 bg-black bg-opacity-0 group-hover:bg-opacity-50 transition duration-300 flex items-center justify-center space-x-4">
                                <button className="opacity-0 group-hover:opacity-100 transform -translate-y-2 group-hover:translate-y-0 transition duration-300">
                                    <FontAwesomeIcon icon={faHeart} className="text-white text-2xl hover:text-red-500" />
                                    <span className="text-white ml-2">1.2k</span>
                                </button>
                                <button className="opacity-0 group-hover:opacity-100 transform -translate-y-2 group-hover:translate-y-0 transition duration-300">
                                    <FontAwesomeIcon icon={faComment} className="text-white text-2xl hover:text-blue-400" />
                                    <span className="text-white ml-2">356</span>
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
        </div>
    );
};

export default AccountPage;