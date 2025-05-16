/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import '../CSS/index.css';
import { useNavigate, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDownload, faTimes, faArrowLeft, faRemove } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import StickyNavbar from '../Components/navBar.jsx';
import { faAdd } from '@fortawesome/free-solid-svg-icons/faAdd';

var jsonData;
function ViewAllImages() {
    const location = useLocation();
    const navigate = useNavigate();
    const receivedData = location.state;
    const [currentImage, setCurrentImage] = useState(0);
    const [isFullscreen, setIsFullscreen] = useState(false);
    console.log(receivedData);
    const removeContent = (contentID) => {
        console.log('remove process : ' + contentID);
    }
    // Handle image download
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

    // Navigation controls
    const handleNext = () => {
        setCurrentImage(prev => (prev + 1) % receivedData.length);
    };

    const handlePrev = () => {
        setCurrentImage(prev => (prev - 1 + receivedData.length) % receivedData.length);
    };
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
                <input type="file" className='m-10 gap-20'/>
                <button 
                    className="m-0 p-3 rounded flex items-center gap-0 text-white-500 bg-blue-400"
                >
                    <FontAwesomeIcon icon={faAdd} />
                    <span className='pl-2'>Add images</span>
                </button>
                <div className="text-gray-600">
                    Showing {receivedData.length} images
                </div>
            </div>
            </div>

            {/* Main Image Grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                {receivedData.map((item, index) => (
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
                                <span>Size: {JSON.parse(item.meta_data).size}</span>
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
                            src={`http://127.0.0.1:10000/devstoreaccount1/postcontents/${receivedData[currentImage].media_name}`}
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
                            {currentImage + 1} / {receivedData.length}
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