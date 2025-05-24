/* eslint-disable no-empty */
/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import '../CSS/index.css';
import { useNavigate, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDownload, faTimes, faArrowLeft, faRemove, faPenAlt } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState, useRef } from 'react';
import StickyNavbar from '../Components/navBar.jsx';
import { faAdd } from '@fortawesome/free-solid-svg-icons/faAdd';
import imageAdd from '../Controller/imageAdd.jsx';
import getIDprocess from '../Controller/getTheuserID';
import removeCache from '../Controller/removeCache';
import removeImage from '../Controller/removeImage.jsx';
import removePost from '../Controller/removePost.jsx';
import updatePost from '../Controller/updatePost.jsx';

var jsonData;
function ViewAllImages() {
    //pic ref
    const picRef = useRef();
    const userID = getIDprocess();
    //image add click handler
    const handlePicSend = async () => {
        const imgAddObj = new imageAdd(picRef,postid,userID);
        var result = await imgAddObj.handleImgInsert();
        if(result == 'Blob uploaded' || result == 'Maximum content amount exceeded' || result == 'Blob did not upload'){
            if(result == 'Blob uploaded'){
                const removeCacheObj = new removeCache(userID);
                const removedResult = await removeCacheObj.removeRequest();
                if(removedResult == 'removed'){
                    alert(result);
                    navigate('/accountPath');
                }else{
                    alert('Error occured when removing cache : '+removedResult);
                    navigate('/accountPath');
                }
            }
        }else{
            alert('Error occured when inserting data (viewAllImages.jsx) : ' + result);
        }
    }
    const location = useLocation();
    const navigate = useNavigate();
    //get the passing data
    const {data,postid} = location.state;
    //remove post obj
    const removePostObj = new removePost(postid);
    //remove cache global
    const removeCacheGlobal = new removeCache(userID);
    //update post obj
    const updatePostObj = new updatePost(postid);
    console.log(data);//FIXME: TEST
    const [currentImage, setCurrentImage] = useState(0);
    const [isFullscreen, setIsFullscreen] = useState(false);

    //to update
    const [isUpdate, setIsUpdate] = useState(false);

    //remove button handler (image)
    const removeContent = async (contentID) => {
        if (confirm("Are you sure you want to remove this image?")) {
        const removeImgObj = new removeImage(contentID);
        const removedResult = await removeImgObj.removeImgProcess();
        if(removedResult == 'Removed content' || removedResult == 'Blob did not removed' || removedResult == 'No such post content'){
            const removeCacheObj = new removeCache(userID);
            const removedResult2 = await removeCacheObj.removeRequest();
            if(removedResult2 == 'removed'){
                alert(removedResult);
            }else{
                alert('Error occured when removing cache : '+removedResult);
            }
        }else{
            alert('Error occured when removing content (viewAllImages) : ' + removedResult)
        }
        navigate('/accountPath');
        }
    }
    //FIXME: FIX THE ERROR
    const handleDownload = async (mediaName) => {
        try {
            const response = await fetch(`http://127.0.0.1:10000/devstoreaccount1/postcontents/${mediaName}`);
            const blob = await response.blob();
            const downloadUrl = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = downloadUrl;
            link.setAttribute('download', mediaName);
            document.body.appendChild(link);
            link.click();
            link.remove();
        } catch (error) {
            console.error('Download failed:', error);
        }
    };

    const updateHandle = () => {
        setIsUpdate(true);
    }
    //FIXME: NEED SOME WORK!!
    const removeHandle = async () => {
        if(confirm('Are you sure you want to remove the post?')){
        const result_of_remove = await removePostObj.processRemove();
       //remove cache
       const result_remove_cache = await removeCacheGlobal.removeRequest();
       //FIXME: test
       console.log(result_of_remove);
       alert(result_of_remove);//TODO: validate the response
       navigate('/accountPath');
        }
    }

    // Navigation controls
    const handleNext = () => {
        setCurrentImage(prev => (prev + 1) % data.length);
    };

    const handlePrev = () => {
        setCurrentImage(prev => (prev - 1 + data.length) % data.length);
    };
    //update handling
    const updateHandler = async () => {
        const titleNew = document.getElementById('titleInput').value;
        var desNew = document.getElementById('desInput').value;
        if(titleNew == ""){
            alert('Titile should not be empty');
        }else{
            if(desNew == ""){
                desNew = "";
            }
             const resultUpdate = await updatePostObj.updateProcess(titleNew,desNew);
        //clear cache
        const result_remove_cache = await removeCacheGlobal.removeRequest();
        console.log(resultUpdate);//FIXME: TEST
        if(resultUpdate.title != null || resultUpdate.title != ""){
            alert('Updated');
        }
        navigate('/accountPath');
        }
    }
    return (
        <div>
            <StickyNavbar></StickyNavbar>
        <div className={`${isFullscreen ? 'fixed inset-0 z-50 bg-black' : 'min-h-screen'} p-4 bg-gray-100`}>
            {/* Header */}
            <div className='flex justify-center items-center'>
                   <div className="flex justify-between items-center mb-6 p-4 bg-white rounded-lg shadow-md sticky top-0 z-10 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                <button 
                    onClick={() => navigate(-1)}
                    className="m-0 p-3 rounded flex items-center gap-0 text-white-500 bg-blue-400"
                >
                    <FontAwesomeIcon icon={faArrowLeft} />
                    <span className='pl-2'>Back to Gallery</span>
                </button>
                {/* //TODO: VALIDATE THE INPUT */}
                <input ref={picRef} type="file" className='bg-slate-500 text-white p-3 rounded'/>
                <button 
                    onClick={() => handlePicSend()}
                    className="m-0 p-3 rounded flex items-center gap-0 text-white-500 bg-blue-400">
                    <FontAwesomeIcon icon={faAdd} />
                    <span className='pl-2'>Add images</span>
                </button>
                <div className="text-gray-600">
                    Showing {data.length} images
                </div>
            </div>
            </div>
<div className='flex justify-center items-center'>
  <div className="mb-6 p-4 bg-white rounded-lg shadow-md sticky top-0 z-10 w-full flex flex-row justify-center gap-4">
    <button 
      onClick={() => removeHandle()}
      className="p-3 rounded flex items-center gap-0 text-white-500 bg-blue-400"
    >
      <FontAwesomeIcon icon={faRemove} />
      <span className='pl-2'>Remove post</span>
    </button>
    <button 
      onClick={() => updateHandle()}
      className="p-3 rounded flex items-center gap-0 text-white-500 bg-blue-400"
    >
      <FontAwesomeIcon icon={faPenAlt} />
      <span className='pl-2'>Update</span>
    </button>
  </div>
</div>

            {/* Main Image Grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                {data.map((item, index) => (
                    <div 
                        key={item.contentid}
                        className="relative group bg-white rounded-xl shadow-lg hover:shadow-xl transition-shadow duration-300"
                    >
                        <img
                            src={`http://127.0.0.1:10000/devstoreaccount1/postcontents/${item.media_name}`}
                            alt={`Content ${index + 1}`}
                            className="w-full h-64 object-cover rounded-t-xl cursor-zoom-in"
                            onClick={() => {
                                setCurrentImage(index);
                                setIsFullscreen(true);
                            }}
                        />
                        
                        {/* Image Overlay Controls */}
                        <div className='grid grid-cols-3 gap-1 relative bottom-10 left-2 justify-items-right'>
                             <button
                                onClick={() => handleDownload(item.media_name)}
                                className='bg-white m-0 p-1 rounded'
                            >
                                <FontAwesomeIcon icon={faDownload} className="text-blue-600 text-xl" />
                            </button>
                            <button
                                className='bg-white m-0 p-1 rounded'
                                onClick={() => removeContent(item.contentid)}
                            >
                                <FontAwesomeIcon icon={faRemove} className="text-blue-600 text-xl" />
                            </button>
                        </div>
                       

                        {/* Image Metadata */}
                        <div className="p-3 pt-0">
                            <div className="flex justify-between items-center text-sm text-gray-600">
                                <span>Size: {JSON.parse(item.meta_data).size}KB</span>
                                <span>{item.type}</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            {/* Fullscreen Viewer */}
            {isFullscreen && (
                <div className="fixed inset-0 bg-black bg-opacity-90 flex items-center justify-center p-4 z-50">
                    <div className="relative max-w-4xl w-full">
                        <div className='relative'>
                                                    <img
                            src={`http://127.0.0.1:10000/devstoreaccount1/postcontents/${data[currentImage].media_name}`}
                            alt="Fullscreen view"
                            className="max-h-[90vh] w-full object-contain"
                        />
                        </div>
                        {/* Navigation Arrows */}
                        <button
                            onClick={handlePrev}
                            className="absolute left-4 top-1/2 -translate-y-1/2 p-4 rounded-full shadow-lg hover:bg-gray-100"
                        >
                            ❮
                        </button>
                        <button
                            onClick={handleNext}
                            className="absolute right-4 top-1/2 -translate-y-1/2 p-4 rounded-full shadow-lg hover:bg-gray-100"
                        >
                            ❯
                        </button>
                        
                        {/* Image Counter */}
                        <div className='relative grid grid-cols-4 gap-4'>
                            <button className='absolute left-30 bottom-2 z-10 bg-white bg-opacity-80 text-black p-2 rounded shadow' onClick={() => setIsFullscreen(false)}>❌</button>
                            <div className="absolute bottom-4 left-1/2 -translate-x-1/2 bg-black bg-opacity-50 text-white px-4 py-2 rounded-full">
                            {currentImage + 1} / {data.length}
                            </div>
                        </div>
                    </div>
                </div>
            )}

            {/**to update post*/}
            {isUpdate && (
        <div className="fixed inset-0 bg-black bg-opacity-90 flex items-center justify-center p-4 z-50">
          <div className="relative max-w-4xl w-full">
            <button
              className="absolute top-4 right-4 text-gray-400 hover:text-white"
            >
              <svg
                className="w-6 h-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
            </button>
            
            <div className="bg-white rounded-lg shadow-xl p-6">
              <div className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">
                    New title
                  </label>
                  <input
                    type="text"
                    id="titleInput"
                    className="p-2 mt-1 block w-full rounded-md bg-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    placeholder="Enter text here"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">
                    New description
                  </label>
                 <textarea
    id="desInput"
    className="mt-1 block w-full rounded-md bg-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm p-2"
    placeholder="Enter more text"
    rows={4}
/>
                </div>
              </div>

              <div className="mt-6 flex gap-4 justify-end">
                <button
                  type="button"
                  onClick={()=>setIsUpdate(false)}
                  className="rounded inline-flex justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm text-black font-medium text-gray-700 hover:bg-gray-50"
                >
                  Cancel
                </button>
                <button
                  onClick={()=>updateHandler()}
                  className="rounded inline-flex justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white hover:bg-indigo-700"
                >
                  Submit
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
        </div>
        </div>
    );
}

export default ViewAllImages;